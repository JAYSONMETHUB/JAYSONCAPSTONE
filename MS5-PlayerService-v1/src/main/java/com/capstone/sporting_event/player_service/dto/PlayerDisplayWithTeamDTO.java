package com.capstone.sporting_event.player_service.dto;

import com.capstone.sporting_event.player_service.models.Team;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDisplayWithTeamDTO {

    private int playerId;

    private String firstName;

    private String lastName;

    private String country;

    private Team team;

}
