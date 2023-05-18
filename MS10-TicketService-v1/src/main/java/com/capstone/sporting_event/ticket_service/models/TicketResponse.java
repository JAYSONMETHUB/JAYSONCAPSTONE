package com.capstone.sporting_event.ticket_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponse {
    int status;
    String message;
}
