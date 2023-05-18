package com.capstone.sporting_event.ticket_service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {



    private int ticketId;
    private String customerName;
    private int matchId;
    private float ticketPrice;


}
