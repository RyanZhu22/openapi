package com.ryan.openapiclientsdk;

import com.ryan.openapiclientsdk.client.OpenApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@ComponentScan
@Configuration
@ConfigurationProperties("openapi.client")
public class OpenApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public OpenApiClient OpenAPiClient() {
        return new OpenApiClient(accessKey, secretKey);
    }

}
