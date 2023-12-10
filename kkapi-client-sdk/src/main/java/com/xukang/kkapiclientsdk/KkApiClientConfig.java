package com.xukang.kkapiclientsdk;

import com.xukang.kkapiclientsdk.clinet.KkApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("kk.client")
@Data
@ComponentScan
public class KkApiClientConfig{
    private String accessKye;
    private String secretKey;

    @Bean
    public KkApiClient kkApiClient() {
        return new KkApiClient(accessKye,secretKey);
    }

}
