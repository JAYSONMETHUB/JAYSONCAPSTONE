package com.capstone.sporting_event.team_service.service;

import com.capstone.sporting_event.team_service.dto.TeamMapper;
import com.capstone.sporting_event.team_service.dto.TeamRequestDTO;
import com.capstone.sporting_event.team_service.entity.Team;
import com.capstone.sporting_event.team_service.exceptions.TeamAlreadyExistException;
import com.capstone.sporting_event.team_service.exceptions.TeamNotFoundException;
import com.capstone.sporting_event.team_service.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepo;

    @Autowired
    TeamMapper teamMapper;

    public Team saveTeam(TeamRequestDTO teamDTO){

        if(teamRepo.findByTeamNameIs(teamDTO.getTeamName()).isPresent())
            throw new TeamAlreadyExistException();

       return teamRepo.save(teamMapper.toEntity(teamDTO));
    }


    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }



    public Team getTeam(int id) {

        Optional<Team> team = teamRepo.findById(id);

        if(team.isEmpty()){
            throw new TeamNotFoundException();
        }

        return team.get();
    }

    public Team getTeamByName(String teamName){
        Optional<Team> team = teamRepo.findByTeamNameIs(teamName);

        if(team.isEmpty()){
            throw new TeamNotFoundException();
        }

        return team.get();
    }


    public Team updateTeam(int id, TeamRequestDTO teamDTO){

        Optional<Team> team = teamRepo.findById(id);

        if(team.isEmpty()){
            throw new TeamNotFoundException();
        }

        Team updatedTeam = team.get();
        updatedTeam.setTeamName(teamDTO.getTeamName());

        return teamRepo.save(updatedTeam);
    }

    public void deleteTeam(int id) {
        Optional<Team> team = teamRepo.findById(id);

        if(team.isEmpty()) {
            throw new TeamNotFoundException();
        }

        Team deletedTeam = team.get();

        deletedTeam.setActive(false);
        teamRepo.save(deletedTeam);
    }


}
