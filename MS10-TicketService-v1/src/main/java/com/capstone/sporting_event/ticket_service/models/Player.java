package com.capstone.sporting_event.ticket_service.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player {

    private int playerId;

    private String firstName;

    private String lastName;

    private String country;

}
