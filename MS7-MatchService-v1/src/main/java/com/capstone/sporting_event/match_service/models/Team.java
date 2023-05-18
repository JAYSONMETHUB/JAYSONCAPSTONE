package com.capstone.sporting_event.match_service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Team {


    private int teamId;

    private String teamName;

    private List<Player> players;
}
