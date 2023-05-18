package com.capstone.sporting_event.team_service.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> requestTemplate.header("X-Forwarded-Host","localhost:8080");
    }
}
