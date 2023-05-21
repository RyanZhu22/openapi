package com.ryan.openapiinterface.controller;

import com.ryan.openapiclientsdk.model.User;
import com.ryan.openapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称API
 *
 * @author ryan
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");

        // TODO 实际情况从数据库中查是否已分配给用户
        if (!accessKey.equals("ryan")) {
            throw new RuntimeException("未授权");
        }
        // TODO 这个出错
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("未授权");
        }
        // TODO 时间和当前时间不超过五分钟
        // Get current instant.
        long currentTimestamp = System.currentTimeMillis() / 1000;
        System.out.println("CURRENTINSTANT: " + currentTimestamp);
        // Check if the timestamp is more than 5 minutes ahead of current time.
        long headerTimestamp = Long.parseLong(timestamp);
        System.out.println(headerTimestamp);
        if ((currentTimestamp - headerTimestamp) > 300) {
            throw new RuntimeException("Timestamp is more than 5 minutes ahead");
        }
        // TODO 实际情况中是从数据库中查出secretKey
        String serverSign = SignUtils.getSign(body, "123123123");
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        return "POST 你的名字是" + user.getUsername();
    }
}
