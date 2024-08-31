package com.eventostec.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Value;

@Configuration
public class AwsConfig {
    @Value("${aws.region}")
    private AwsRegion;

    @Bean
    public AmazonS3 createS3Instance(){
        return AmazonS3clientBuider.standart()
                .withRegion(AwsRegion)
                .build();
    }


}
