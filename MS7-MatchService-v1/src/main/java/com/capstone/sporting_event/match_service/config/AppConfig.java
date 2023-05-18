package com.capstone.sporting_event.match_service.config;

import com.capstone.sporting_event.match_service.dto.MatchMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MatchMapper matchMapper(){ return new MatchMapper();}

}
