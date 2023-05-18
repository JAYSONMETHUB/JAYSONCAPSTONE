package com.capstone.sporting_event.player_service.config;

import com.capstone.sporting_event.player_service.dto.PlayerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public PlayerMapper playerMapper(){ return new PlayerMapper(); }

}
