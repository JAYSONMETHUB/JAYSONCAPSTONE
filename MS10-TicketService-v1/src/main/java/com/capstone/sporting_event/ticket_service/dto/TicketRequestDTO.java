package com.capstone.sporting_event.ticket_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {


    @NotNull(message="Ticket name should not be null")
    @NotBlank(message="Ticket name should not be blank")
    @NotEmpty(message="Ticket name should not be empty")
    @Size(min=2,message="Ticket name should be at least 2 characters long")
    private String customerName;


    @NotNull(message="Match id should not be null")
    private int matchId;


    @NotNull(message="Ticket price should not be null")
    private float ticketPrice;
}
