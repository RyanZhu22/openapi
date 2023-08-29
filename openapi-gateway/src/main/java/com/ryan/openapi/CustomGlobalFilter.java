package com.ryan.openapi;

import cn.hutool.crypto.SignUtil;
import com.ryan.openapi.common.model.entity.InterfaceInfo;
import com.ryan.openapi.common.model.entity.User;
import com.ryan.openapi.common.service.InnerInterfaceInfoService;
import com.ryan.openapi.common.service.InnerUserInterfaceInfoService;
import com.ryan.openapi.common.service.InnerUserService;
import com.ryan.openapiclientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    private static final String INTERFACE_HOST = "http://localhost:8123";


    /**
     * global filter
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        1. 请求日志
        ServerHttpRequest request = exchange.getRequest();
        String path = INTERFACE_HOST + request.getPath().value();
        String method = request.getMethod().toString();
        log.info("unique flag in request: " + request.getId());
        log.info("path in request: " + path);
        log.info("method in request: " + method);
        log.info("params in request: " + request.getQueryParams());
        String sourceAddress = request.getLocalAddress().getHostString();
        log.info("source path in request: " + sourceAddress);
        log.info("source path in request: " + request.getRemoteAddress());
        ServerHttpResponse response = exchange.getResponse();
//        2. 黑白名单
        if (!IP_WHITE_LIST.contains(sourceAddress)) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
//        3. 网关进行用户鉴权（判断ak、sk是否合法）
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        // TODO 服务调用：实际情况中是从数据库中查询是否分配给用户
        User invokeUser = null;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error", e);
        }
        if (invokeUser == null) {
            return handleNoAuth(response);
        }
        if (Long.parseLong(nonce) > 10000) {
            return handleNoAuth(response);
        }
        // TODO 时间和当前时间不超过五分钟
        Long currentTime = System.currentTimeMillis() / 1000;
        Long FIVE_MINUTES = 60 * 5L;
        if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
            return handleNoAuth(response);
        }
        // TODO 实际情况中是从数据库中查出secretKey
        String secretKey = invokeUser.getSecretKey();
        String serverSign = SignUtils.getSign(body, secretKey);
        if (sign == null || !sign.equals(serverSign)) {
            return handleNoAuth(response);
        }
//        4. 请求模拟接口是否存在
        // TODO 服务调用：从数据库中查询模拟接口是否存在，以及存请求方法是否匹配，(还可以校验请求参数）
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("getInterfaceInfo error", e);
        }
        if (interfaceInfo == null) {
            return handleNoAuth(response);
        }
//        5. 请求转发，调用模拟接口 + 响应日志
//        Mono<Void> filter = chain.filter(exchange);
        // return filter;
        return handleResponse(exchange, chain, interfaceInfo.getId(), invokeUser.getId());

    }

    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long interfaceInfoId, long userId) {
        try {

            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        //log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 返回值写数据
                            // 拼接字符串
                            return super.writeWith(
                                fluxBody.map(dataBuffer -> {
                                    // 7. TODO 服务调用：调用成功，接口调用次数+1 + 1 invokeCount
                                    try {
                                        innerUserInterfaceInfoService.invokeCount(interfaceInfoId, userId);
                                    } catch (Exception e) {
                                        log.error("invokeCount error", e);
                                    }
                                    byte[] content = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(content);
                                    DataBufferUtils.release(dataBuffer);//释放掉内存
                                    // 构建日志
                                    StringBuilder sb2 = new StringBuilder(200);
                                    sb2.append("<--- {} {} \n");
                                    List<Object> rspArgs = new ArrayList<>();
                                    rspArgs.add(originalResponse.getStatusCode());
                                    //rspArgs.add(requestUrl);
                                    String data = new String(content, StandardCharsets.UTF_8);//data
                                    sb2.append(data);
                                    // 打印日志
                                    log.info("response result: " + data);
                                    return bufferFactory.wrap(content);
                                }));
                        } else {
                            log.error("<--- {} 响应code异常", getStatusCode());
                        } return super.writeWith(body);
                    }
                }; return chain.filter(exchange.mutate().response(decoratedResponse).build());
            } return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            log.error("gateway log exception.\n" + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    public Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}