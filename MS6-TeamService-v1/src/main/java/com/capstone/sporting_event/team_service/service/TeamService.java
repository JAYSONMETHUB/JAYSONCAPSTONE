package com.capstone.sporting_event.team_service.service;

import com.capstone.sporting_event.team_service.dto.TeamRequestDTO;
import com.capstone.sporting_event.team_service.entity.Team;
import com.capstone.sporting_event.team_service.exceptions.TeamAlreadyExistException;
import com.capstone.sporting_event.team_service.exceptions.TeamNotFoundException;

import java.util.List;

public interface TeamService {
    public Team saveTeam(TeamRequestDTO teamDTO) throws TeamAlreadyExistException;

    public List<Team> getAllTeams();
    public Team getTeam(int id) throws TeamNotFoundException;
    public Team getTeamByName(String name) throws TeamNotFoundException;

    public Team updateTeam(int id, TeamRequestDTO teamDTO) throws TeamNotFoundException;

    public void deleteTeam(int id) throws TeamNotFoundException;

}
