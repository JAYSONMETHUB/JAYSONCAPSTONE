package com.capstone.sporting_event.ticket_service.dto;

import com.capstone.sporting_event.ticket_service.models.Match;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDisplayDTO {

    private int ticketId;
    private String customerName;
    private Match match;
    private float ticketPrice;
}
