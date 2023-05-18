package com.capstone.sporting_event.ticket_service.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tournament {


    private int tournamentId;
    private String tournamentName;
    private String sportsCategory;
    private String tournamentStyle;

}
