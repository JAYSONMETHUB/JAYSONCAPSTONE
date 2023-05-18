package com.capstone.sporting_event.team_service;


import com.capstone.sporting_event.team_service.entity.Team;
import com.capstone.sporting_event.team_service.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)

class TeamEntityTests {


    int id = 1;
    String teamName = "jayson";

    boolean active = true;

    @Autowired
    private TeamRepository teamRepo;


    // TEST GETTERS,SETTERS

    @Test
    void test_Getters_And_Setters(){


        Team team = new Team();

        team.setTeamId(id);
        team.setTeamName(teamName);
        team.setActive(active);
       
      

        String toString = team.toString();

        assertEquals(team.getTeamId(),id);
        assertEquals(team.getTeamName(),teamName);
        assertEquals(team.isActive(),active);
        assertNotNull(toString);

    }

    @Test
    void test_Builder(){


        Team team = Team.builder()
                .teamId(id)
                .teamName(teamName)
                .isActive(active)
                .build();

        String toString = team.toString();
        assertNotNull(toString);

        assertEquals(team.getTeamId(),id);
        assertEquals(team.getTeamName(),teamName);
        assertEquals(team.isActive(),active);

    }



    //TEST CONSTRAINTS

    @Test
    void should_Throw_Exception_If_Team_FirstName_Is_Empty(){
            Team team = Team.builder()
                    .teamName(null)
                    .teamId(1)
                    .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            teamRepo.saveAndFlush(team);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [team_name\" of relation \"team]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }






}
