package com.capstone.sporting_event.team_service.dto;

import com.capstone.sporting_event.team_service.entity.Team;
import com.capstone.sporting_event.team_service.feign.PlayerClient;
import com.capstone.sporting_event.team_service.models.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeamMapper {

    @Autowired
    PlayerClient playerClient;

    public TeamRequestDTO toRequestDTO(Team entity){

        return TeamRequestDTO.builder()
                .teamName(entity.getTeamName())
                .build();
    }

    public TeamDisplayDTO toDisplayDTO(Team entity){


        return TeamDisplayDTO.builder()
                .teamId(entity.getTeamId())
                .teamName(entity.getTeamName())
                .build();
    }

    public TeamDisplayWithPlayersDTO toDisplayWithPlayersDTO(Team entity){

        List<Player> playerList = playerClient.getPlayerByTeamName(entity.getTeamName());

        return TeamDisplayWithPlayersDTO.builder()
                .teamId(entity.getTeamId())
                .teamName(entity.getTeamName())
                .players(playerList)
                .build();
    }


    public Team toEntity(TeamRequestDTO dto){

        return Team.builder()
                .teamName(dto.getTeamName())
                .build();
    }





}
