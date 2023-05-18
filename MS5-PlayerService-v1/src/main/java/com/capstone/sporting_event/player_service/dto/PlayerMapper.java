package com.capstone.sporting_event.player_service.dto;


import com.capstone.sporting_event.player_service.entity.Player;
import com.capstone.sporting_event.player_service.feign.TeamClient;
import com.capstone.sporting_event.player_service.models.Team;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerMapper {

    @Autowired
    TeamClient teamClient;
    public PlayerRequestDTO toRequestDTO(Player entity){
        String teamName = null;

        if(entity.getTeamId()!=null) {
             teamName = teamClient.getTeamById(entity.getTeamId()).getTeamName();
        }
        return PlayerRequestDTO.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .country(entity.getCountry())
                .teamName(teamName)
                .build();
    }

    public PlayerDisplayDTO toDisplayDTO(Player entity){



        return PlayerDisplayDTO.builder()
                .playerId(entity.getPlayerId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .country(entity.getCountry())
                .teamId(entity.getTeamId())
                .build();
    }

    public PlayerDisplayWithTeamDTO toDisplayDTOWithTeam(Player entity){

        Team team = null;

        if(entity.getTeamId()!=null){
             team = teamClient.getTeamById(entity.getTeamId());
        }

        return PlayerDisplayWithTeamDTO.builder()
                .playerId(entity.getPlayerId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .country(entity.getCountry())
                .team(team)
                .build();
    }

    public Player toEntity(PlayerRequestDTO dto){

        Integer teamId = null;

        if(dto.getTeamName()!=null) {
            teamId = teamClient.getTeamByName(dto.getTeamName()).getTeamId();
        }
        return Player.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .country(dto.getCountry())
                .teamId(teamId)
                .build();
    }





}
