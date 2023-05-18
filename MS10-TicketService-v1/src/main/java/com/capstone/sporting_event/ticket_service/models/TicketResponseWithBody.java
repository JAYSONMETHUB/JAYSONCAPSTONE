package com.capstone.sporting_event.ticket_service.models;

import com.capstone.sporting_event.ticket_service.dto.TicketDisplayDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponseWithBody
{

    int status;
    String message;
    TicketDisplayDTO body;
}
