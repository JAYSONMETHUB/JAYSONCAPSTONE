package com.capstone.sporting_event.team_service.controller;

import com.capstone.sporting_event.team_service.dto.TeamDisplayDTO;
import com.capstone.sporting_event.team_service.dto.TeamDisplayWithPlayersDTO;
import com.capstone.sporting_event.team_service.dto.TeamMapper;
import com.capstone.sporting_event.team_service.dto.TeamRequestDTO;
import com.capstone.sporting_event.team_service.models.TeamResponse;
import com.capstone.sporting_event.team_service.models.TeamResponseWithBody;
import com.capstone.sporting_event.team_service.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.team_service.constants.Mapping.VERSION_1;
import static com.capstone.sporting_event.team_service.constants.MessageMap.*;


@RestController
@RequestMapping(value=VERSION_1)
public class TeamController {
    
    @Autowired
    TeamService teamService;
    @Autowired
    TeamMapper teamMapper;
    @GetMapping("/team/id/{teamId}")
    public TeamDisplayDTO getTeamById(@PathVariable int teamId) {
        return teamMapper.toDisplayDTO(teamService.getTeam(teamId));
    }

    @GetMapping("/team/name/{teamName}")
    public TeamDisplayDTO getTeamByName(@PathVariable String teamName) {
        return teamMapper.toDisplayDTO(teamService.getTeamByName(teamName));
    }

    @GetMapping("/team/id/{teamId}/players")
    public TeamDisplayWithPlayersDTO getTeamByNameWithPlayers(@PathVariable int teamId) {
        return teamMapper.toDisplayWithPlayersDTO(teamService.getTeam(teamId));
    }
    @GetMapping("/team/name/{teamName}/players")
    public TeamDisplayWithPlayersDTO getTeamByNameWithPlayers(@PathVariable String teamName) {
        return teamMapper.toDisplayWithPlayersDTO(teamService.getTeamByName(teamName));
    }

    @GetMapping("/teams")
    public List<TeamDisplayWithPlayersDTO> getTeams() {
        return teamService.getAllTeams().stream().map(team->teamMapper.toDisplayWithPlayersDTO(team)).collect(Collectors.toList());
    }

    @PostMapping("/team")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeamResponseWithBody> addTeam(@RequestBody TeamRequestDTO teamDTO){

        TeamResponseWithBody saveTeamResponse = TeamResponseWithBody.builder()
                .status(201)
                .message(SAVE_TEAM_SUCCESS)
                .body(teamMapper.toDisplayDTO(teamService.saveTeam(teamDTO)))
                .build();



        return new ResponseEntity<>(saveTeamResponse, HttpStatus.CREATED);
    }

    @PutMapping("/team/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeamResponseWithBody> updateTeam(@PathVariable int teamId, @Valid @RequestBody TeamRequestDTO teamRequestDTO){

        TeamResponseWithBody updateTeamResponse = TeamResponseWithBody.builder()
                .status(204)
                .message(UPDATE_TEAM_SUCCESS)
                .body(teamMapper.toDisplayDTO(teamService.updateTeam(teamId, teamRequestDTO)))
                .build();

        return new ResponseEntity<>(updateTeamResponse, HttpStatus.OK);
    }

    @DeleteMapping("/team/{teamId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<TeamResponse> deleteTeam(@PathVariable("teamId") int teamId){

        teamService.deleteTeam(teamId);

        TeamResponse deleteTeamResponse = TeamResponse.builder()
                .status(202)
                .message(DELETE_TEAM_SUCCESS)
                .build();

        return new ResponseEntity<>(deleteTeamResponse, HttpStatus.ACCEPTED);
    }

}
