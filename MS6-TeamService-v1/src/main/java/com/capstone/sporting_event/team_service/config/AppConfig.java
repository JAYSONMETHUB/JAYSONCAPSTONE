package com.capstone.sporting_event.team_service.config;

import com.capstone.sporting_event.team_service.dto.TeamMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TeamMapper teamMapper(){ return new TeamMapper();}

}
