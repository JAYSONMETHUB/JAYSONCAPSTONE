package com.capstone.sporting_event.player_service;


import com.capstone.sporting_event.player_service.entity.Player;
import com.capstone.sporting_event.player_service.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
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

class PlayerEntityTests {


    int id = 1;
    String firstName = "jayson";
    String lastName = "concepcion";
    boolean active = true;
    String country = "philippines";
    int teamId = 1;

    @Autowired
    private PlayerRepository playerRepo;


    // TEST GETTERS,SETTERS

    @Test
    void test_Getters_And_Setters(){


        Player player = new Player();

        player.setPlayerId(id);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setCountry(country);
        player.setTeamId(teamId);
        player.setActive(active);
       
      

        String toString = player.toString();

        assertEquals(player.getPlayerId(),id);
        assertEquals(player.getFirstName(),firstName);
        assertEquals(player.getLastName(),lastName);
        assertEquals(player.getCountry(),country);
        assertEquals(player.getTeamId(),teamId);
        assertEquals(player.isActive(),active);
        assertNotNull(toString);

    }

    @Test
    void test_Builder(){


        Player player = Player.builder()
                .playerId(id)
                .firstName(firstName)
                .lastName(lastName)
                .isActive(active)
                .country(country)
                .teamId(teamId)
                .build();

        String toString = player.toString();
        assertNotNull(toString);

        assertEquals(player.getPlayerId(),id);
        assertEquals(player.getFirstName(),firstName);
        assertEquals(player.getLastName(),lastName);
        assertEquals(player.getCountry(),country);
        assertEquals(player.getTeamId(),teamId);
        assertEquals(player.isActive(),active);

    }



    //TEST CONSTRAINTS

    @Test
    void should_Throw_Exception_If_Player_FirstName_Is_Empty(){
            Player player = Player.builder()
                    .firstName(null)
                    .lastName("concepcion")
                    .country("testcountry")
                    .teamId(1)
                    .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            playerRepo.saveAndFlush(player);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [first_name\" of relation \"player]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void should_Throw_Exception_If_Player_LastName_Is_Empty(){
        Player player = Player.builder()
                .firstName("jconcepcion")
                .lastName(null)
                .country("testcountry")
                .teamId(1)
                .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            playerRepo.saveAndFlush(player);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [last_name\" of relation \"player]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void should_Throw_Exception_If_Player_Country_Is_Empty(){
        Player player = Player.builder()
                .firstName("jconcepcion")
                .lastName("test")
                .country(null)
                .teamId(1)
                .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            playerRepo.saveAndFlush(player);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [country\" of relation \"player]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }






}
