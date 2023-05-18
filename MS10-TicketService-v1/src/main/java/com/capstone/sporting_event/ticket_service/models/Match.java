package com.capstone.sporting_event.ticket_service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Match {



    private int matchId;

    private Field field;

    private Tournament tournament;

    private List<Team> teams;

    private Date dateTime;




}
