package com.capstone.sporting_event.tournament_service.config;

import com.capstone.sporting_event.tournament_service.dto.TournamentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TournamentMapper tournamentMapper(){ return new TournamentMapper();}

}
