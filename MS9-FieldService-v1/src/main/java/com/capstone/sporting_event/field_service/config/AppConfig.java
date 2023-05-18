package com.capstone.sporting_event.field_service.config;

import com.capstone.sporting_event.field_service.dto.FieldMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FieldMapper fieldMapper(){ return new FieldMapper();}

}
