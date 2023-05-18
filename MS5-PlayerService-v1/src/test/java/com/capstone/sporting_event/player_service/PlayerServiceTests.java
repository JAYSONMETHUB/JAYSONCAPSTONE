package com.capstone.sporting_event.player_service;


import com.capstone.sporting_event.player_service.dto.PlayerDisplayDTO;
import com.capstone.sporting_event.player_service.dto.PlayerMapper;
import com.capstone.sporting_event.player_service.dto.PlayerRequestDTO;
import com.capstone.sporting_event.player_service.entity.Player;
import com.capstone.sporting_event.player_service.exceptions.PlayerNotFoundException;
import com.capstone.sporting_event.player_service.feign.TeamClient;
import com.capstone.sporting_event.player_service.models.Team;
import com.capstone.sporting_event.player_service.repository.PlayerRepository;
import com.capstone.sporting_event.player_service.service.PlayerServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PlayerServiceTests {

    @Mock
    private PlayerRepository playerRepo;

    @Mock
    private PlayerMapper playerMapper;
    @Mock
    private TeamClient teamClient;
    @InjectMocks
    private PlayerServiceImpl playerService;



    private Player playerEntity;


    @BeforeEach
    public void setup(){

        playerEntity = Player.builder()
                .firstName("jayson")
                .lastName("concepcion")
                .country("philippines")
                .teamId(1)
                .build();
    }



    @Test
    void should_Be_Able_To_Get_Player()
    {
        PlayerDisplayDTO expected = PlayerDisplayDTO.builder()
                .firstName("jayson")
                .lastName("concepcion")
                .country("philippines")
                .teamId(1).build();

        when(playerRepo.findById(anyInt())).thenReturn(Optional.ofNullable(playerEntity));
        when(playerMapper.toDisplayDTO(any(Player.class))).thenReturn(expected);

        PlayerDisplayDTO playerDTO = playerMapper.toDisplayDTO(playerService.getPlayer(1));

        assertNotNull(playerDTO);
        assertEquals(expected.getFirstName(), playerDTO.getFirstName());
        assertEquals(expected.getLastName(), playerDTO.getLastName());

        assertEquals(expected.getCountry(), playerDTO.getCountry());

    }



    @Test
    void should_Be_Able_To_Get_All_Player() {
        PlayerRequestDTO playerRequest = PlayerRequestDTO.builder()
                .firstName("jayson")
                .country("philippines")
                .lastName("concepcion")
                .build();

        when(playerRepo.findAll()).thenReturn(List.of(playerEntity));

        List<Player> players = playerService.getAllPlayers();

        assertEquals(1,players.size());
        assertEquals(playerEntity,players.get(0));


    }
    @Test
    void should_Throw_Player_Not_Found_Exception()
    {

       when(playerRepo.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> {

            playerService.getPlayer(1);

        });
    }

    @Test
    void should_Be_Able_To_Save_Player()
    {
       Player expected = playerEntity;

        Team newteam = Team.builder()
                .teamId(1)
                .teamName("testname")
                .build();

        PlayerRequestDTO playerRequest = PlayerRequestDTO.builder()
                .firstName("jayson")
                .country("philippines")
                .lastName("concepcion")
                .teamName("test")
                .build();


        when(playerRepo.save(any(Player.class))).thenReturn(playerEntity);
        when(teamClient.getTeamByName(playerRequest.getTeamName())).thenReturn(newteam);



        Player savedPlayer = playerService.savePlayer(playerRequest);

        assertNotNull(savedPlayer);
        assertEquals(expected.getFirstName(), playerRequest.getFirstName());
        assertEquals(expected.getLastName(), playerRequest.getLastName());
        assertEquals(expected.getCountry(), playerRequest.getCountry());

    }



    @Test
    void should_Be_Able_To_Update_Player() {
        PlayerRequestDTO playerRequest = PlayerRequestDTO.builder()
                .firstName("jayson")
                .country("philippines")
                .lastName("concepcion")
                .teamName("testname")
                .build();

        Team newteam = Team.builder()
                .teamId(1)
                .teamName("testname")
                .build();

        Player expectedPlayer = Player.builder()
                .firstName("updatedPlayer")
                .country("update@country.com")
                .lastName("updatedPassword")
                .build();

        when(teamClient.getTeamByName(playerRequest.getTeamName())).thenReturn(newteam);
        when(playerRepo.findById(1)).thenReturn(Optional.ofNullable(playerEntity));
        when(playerRepo.save(any(Player.class))).thenReturn(expectedPlayer);

        Player actualPlayer = playerService.updatePlayer(1,playerRequest);


        assertNotNull(actualPlayer);

        assertEquals(expectedPlayer.getFirstName(),actualPlayer.getFirstName());
        assertEquals(expectedPlayer.getLastName(),actualPlayer.getLastName());
        assertEquals(expectedPlayer.getCountry(),actualPlayer.getCountry());



    }



    @Test
    void should_Be_Able_To_Delete_Player() {
        PlayerRequestDTO playerRequest = PlayerRequestDTO.builder()
                .firstName("jayson")
                .country("philippines")
                .lastName("concepcion")
                .build();


        when(playerRepo.findById(1)).thenReturn(Optional.ofNullable(playerEntity));

        playerService.deletePlayer(1);

        verify(playerRepo).save(any(Player.class));


    }

    @Test
    void should_Throw_Player_Not_Found_In_Delete_Player() {


        when(playerRepo.findById(1)).thenReturn(Optional.empty());



        Exception exception = assertThrows(PlayerNotFoundException.class, () -> {

            playerService.deletePlayer(1);

        });

        String expectedMessage = "com.capstone.sporting_event.player_service.exceptions.PlayerNotFoundException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }



    }


