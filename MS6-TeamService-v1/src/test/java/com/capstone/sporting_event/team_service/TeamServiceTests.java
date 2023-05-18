package com.capstone.sporting_event.team_service;


import com.capstone.sporting_event.team_service.dto.TeamDisplayDTO;
import com.capstone.sporting_event.team_service.dto.TeamMapper;
import com.capstone.sporting_event.team_service.dto.TeamRequestDTO;
import com.capstone.sporting_event.team_service.entity.Team;
import com.capstone.sporting_event.team_service.exceptions.TeamNotFoundException;
import com.capstone.sporting_event.team_service.feign.PlayerClient;
import com.capstone.sporting_event.team_service.models.Player;
import com.capstone.sporting_event.team_service.repository.TeamRepository;
import com.capstone.sporting_event.team_service.service.TeamServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TeamServiceTests {

    @Mock
    private TeamRepository teamRepo;

    @Mock
    private TeamMapper teamMapper;
    @Mock
    private PlayerClient playerClient;
    @InjectMocks
    private TeamServiceImpl teamService;



    private Team teamEntity;


    @BeforeEach
    public void setup(){

        teamEntity = Team.builder()
                .teamName("jayson")
                .teamId(1)
                .build();
    }



    @Test
    void should_Be_Able_To_Get_Team()
    {
        TeamDisplayDTO expected = TeamDisplayDTO.builder()
                .teamName("jayson")
                .teamId(1).build();

        when(teamRepo.findById(anyInt())).thenReturn(Optional.ofNullable(teamEntity));
        when(teamMapper.toDisplayDTO(any(Team.class))).thenReturn(expected);

        TeamDisplayDTO teamDTO = teamMapper.toDisplayDTO(teamService.getTeam(1));

        assertNotNull(teamDTO);
        assertEquals(expected.getTeamName(), teamDTO.getTeamName());
        


    }


    @Test
    void should_Be_Able_To_Get_Team_ByName()
    {
        TeamDisplayDTO expected = TeamDisplayDTO.builder()
                .teamName("jayson")
                .teamId(1).build();

        when(teamRepo.findByTeamNameIs(anyString())).thenReturn(Optional.ofNullable(teamEntity));
        when(teamMapper.toDisplayDTO(any(Team.class))).thenReturn(expected);

        TeamDisplayDTO teamDTO = teamMapper.toDisplayDTO(teamService.getTeamByName("jayson"));


        assertNotNull(teamDTO);
        assertEquals(expected.getTeamName(), teamDTO.getTeamName());

    }
    @Test
    void should_Be_Able_To_Get_All_Team() {
        TeamRequestDTO teamRequest = TeamRequestDTO.builder()
                .teamName("jayson")
                .build();

        when(teamRepo.findAll()).thenReturn(List.of(teamEntity));

        List<Team> teams = teamService.getAllTeams();

        assertEquals(1,teams.size());
        assertEquals(teamEntity,teams.get(0));


    }


    @Test
    void should_Throw_Team_Not_Found_Exception()
    {

       when(teamRepo.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(TeamNotFoundException.class, () -> {

            teamService.getTeam(1);

        });
    }

    @Test
    void should_Be_Able_To_Save_Team()
    {
       Team expected = teamEntity;

        Team newteam = Team.builder()
                .teamId(1)
                .teamName("testname")
                .build();

        TeamRequestDTO teamRequest = TeamRequestDTO.builder()
                .teamName("jayson")
                .build();


        when(teamRepo.save(any(Team.class))).thenReturn(teamEntity);
        when(teamMapper.toEntity(any(TeamRequestDTO.class))).thenReturn(teamEntity);


        Team savedTeam = teamService.saveTeam(teamRequest);

        assertNotNull(savedTeam);
        assertEquals(expected.getTeamName(), teamRequest.getTeamName());
 

    }



    @Test
    void should_Be_Able_To_Update_Team() {
        TeamRequestDTO teamRequest = TeamRequestDTO.builder()
                .teamName("jayson")
                .build();

        Team newteam = Team.builder()
                .teamId(1)
                .teamName("testname")
                .build();

        Team expectedTeam = Team.builder()
                .teamName("updatedTeam")
                .build();

       // when(teamClient.getTeamByName(teamRequest.getTeamName())).thenReturn(newteam);
        when(teamRepo.findById(1)).thenReturn(Optional.ofNullable(teamEntity));
        when(teamRepo.save(any(Team.class))).thenReturn(expectedTeam);

        Team actualTeam = teamService.updateTeam(1,teamRequest);


        assertNotNull(actualTeam);

        assertEquals(expectedTeam.getTeamName(),actualTeam.getTeamName());



    }



    @Test
    void should_Be_Able_To_Delete_Team() {
        TeamRequestDTO teamRequest = TeamRequestDTO.builder()
                .teamName("jayson")
                .build();


        when(teamRepo.findById(1)).thenReturn(Optional.ofNullable(teamEntity));

        teamService.deleteTeam(1);

        verify(teamRepo).save(any(Team.class));


    }

    @Test
    void should_Throw_Team_Not_Found_In_Delete_Team() {


        when(teamRepo.findById(1)).thenReturn(Optional.empty());



        Exception exception = assertThrows(TeamNotFoundException.class, () -> {

            teamService.deleteTeam(1);

        });

        String expectedMessage = "com.capstone.sporting_event.team_service.exceptions.TeamNotFoundException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }



    }


