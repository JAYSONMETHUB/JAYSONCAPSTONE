package com.capstone.sporting_event.match_service.dto;

import com.capstone.sporting_event.match_service.entity.Match;
import com.capstone.sporting_event.match_service.exceptions.InvalidMatchDetailsException;
import com.capstone.sporting_event.match_service.feign.FieldClient;
import com.capstone.sporting_event.match_service.feign.TeamClient;
import com.capstone.sporting_event.match_service.feign.TournamentClient;
import com.capstone.sporting_event.match_service.models.Field;
import com.capstone.sporting_event.match_service.models.Team;
import com.capstone.sporting_event.match_service.models.Tournament;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MatchMapper {


    @Autowired
    FieldClient fieldClient;
    @Autowired
    TeamClient teamClient;
    @Autowired
    TournamentClient tournamentClient;

    public MatchRequestDTO toRequestDTO(Match entity){

        int[] teamsId =  Stream.of(entity.getTeamsId().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        return MatchRequestDTO.builder()
                .fieldId(entity.getFieldId())
                .tournamentId(entity.getTournamentId())
                .teamsId(teamsId)
                .dateTime(entity.getDateTime())
                .build();
    }

    public MatchDisplayDTO toDisplayDTO(Match entity){

        Field field = fieldClient.getFieldById(entity.getFieldId());

        Tournament tournament = tournamentClient.getTournamentById(entity.getTournamentId());

        int[] teamsId =  Stream.of(entity.getTeamsId().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Team> teams = Arrays.stream(teamsId).mapToObj(id->teamClient.getTeamById(id)).collect(Collectors.toList());


        return MatchDisplayDTO.builder()
                .matchId(entity.getMatchId())
                .field(field)
                .tournament(tournament)
                .teams(teams)
                .dateTime(entity.getDateTime())
                .build();
    }



    public Match toEntity(MatchRequestDTO dto){

        String teamsId = IntStream.of(dto.getTeamsId())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        try {

            fieldClient.getFieldById(dto.getFieldId());

            tournamentClient.getTournamentById(dto.getTournamentId());

            Arrays.stream(dto.getTeamsId()).mapToObj(id->teamClient.getTeamById(id)).collect(Collectors.toList());


        }catch(RuntimeException e){
            throw new InvalidMatchDetailsException();
        }


        return Match.builder()
                .fieldId(dto.getFieldId())
                .tournamentId(dto.getTournamentId())
                .teamsId(teamsId)
                .dateTime(dto.getDateTime())

                .build();
    }



}
