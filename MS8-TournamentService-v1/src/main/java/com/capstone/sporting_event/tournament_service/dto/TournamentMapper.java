package com.capstone.sporting_event.tournament_service.dto;

import com.capstone.sporting_event.tournament_service.entity.Tournament;


public class TournamentMapper {


    public TournamentRequestDTO toRequestDTO(Tournament entity){

        return TournamentRequestDTO.builder()
                .tournamentName(entity.getTournamentName())
                .sportsCategory(entity.getSportsCategory())
                .tournamentStyle(entity.getTournamentStyle())
                .build();
    }

    public TournamentDisplayDTO toDisplayDTO(Tournament entity){


        return TournamentDisplayDTO.builder()
                .tournamentId(entity.getTournamentId())
                .tournamentName(entity.getTournamentName())
                .sportsCategory(entity.getSportsCategory())
                .tournamentStyle(entity.getTournamentStyle())
                .build();
    }



    public Tournament toEntity(TournamentRequestDTO dto){

        return Tournament.builder()
                .tournamentName(dto.getTournamentName())
                .sportsCategory(dto.getSportsCategory())
                .tournamentStyle(dto.getTournamentStyle())
                .build();
    }



}
