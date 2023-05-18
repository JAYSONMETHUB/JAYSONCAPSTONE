package com.capstone.sporting_event.ticket_service.dto;

import com.capstone.sporting_event.ticket_service.entity.Ticket;
import com.capstone.sporting_event.ticket_service.exceptions.InvalidMatchIdException;
import com.capstone.sporting_event.ticket_service.feign.MatchClient;
import com.capstone.sporting_event.ticket_service.models.Match;
import org.springframework.beans.factory.annotation.Autowired;


public class TicketMapper {


    @Autowired
    MatchClient matchClient;


    public TicketRequestDTO toRequestDTO(Ticket entity){

        return TicketRequestDTO.builder()
                .customerName(entity.getCustomerName())
                .matchId(entity.getMatchId())
                .ticketPrice(entity.getTicketPrice())
                .build();
    }

    public TicketDisplayDTO toDisplayDTO(Ticket entity){


        Match match = matchClient.getTeamById(entity.getMatchId());

        return TicketDisplayDTO.builder()
                .ticketId(entity.getTicketId())
                .customerName(entity.getCustomerName())
                .match(match)
                .ticketPrice(entity.getTicketPrice())
                .build();
    }



    public Ticket toEntity(TicketRequestDTO dto){

        return Ticket.builder()
                .customerName(dto.getCustomerName())
                .matchId(dto.getMatchId())
                .ticketPrice(dto.getTicketPrice())
                .build();

    }


}
