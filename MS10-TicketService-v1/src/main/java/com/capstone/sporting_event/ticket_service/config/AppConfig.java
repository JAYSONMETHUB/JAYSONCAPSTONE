package com.capstone.sporting_event.ticket_service.config;

import com.capstone.sporting_event.ticket_service.dto.TicketMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TicketMapper ticketMapper(){ return new TicketMapper();}

}
