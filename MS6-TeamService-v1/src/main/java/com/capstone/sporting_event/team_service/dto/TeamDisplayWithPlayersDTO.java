package com.capstone.sporting_event.team_service.dto;

import com.capstone.sporting_event.team_service.models.Player;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamDisplayWithPlayersDTO {

    private int teamId;

    private String teamName;

    private List<Player> players;
}
