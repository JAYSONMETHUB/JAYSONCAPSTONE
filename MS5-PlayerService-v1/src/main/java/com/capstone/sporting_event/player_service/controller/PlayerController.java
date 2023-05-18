package com.capstone.sporting_event.player_service.controller;

import com.capstone.sporting_event.player_service.dto.PlayerDisplayDTO;
import com.capstone.sporting_event.player_service.dto.PlayerDisplayWithTeamDTO;
import com.capstone.sporting_event.player_service.dto.PlayerMapper;
import com.capstone.sporting_event.player_service.dto.PlayerRequestDTO;
import com.capstone.sporting_event.player_service.models.PlayerResponse;
import com.capstone.sporting_event.player_service.models.PlayerResponseWithBody;
import com.capstone.sporting_event.player_service.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.player_service.constants.Mapping.VERSION_1;
import static com.capstone.sporting_event.player_service.constants.MessageMap.*;

@RestController
@RequestMapping(value=VERSION_1)
public class PlayerController {
    
    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerMapper playerMapper;

    @GetMapping("/player/id/{playerId}")
    public PlayerDisplayDTO getPlayer(@NotNull @PathVariable int playerId) {
        return playerMapper.toDisplayDTO(playerService.getPlayer(playerId));
    }
    @GetMapping("/players")
    public List<PlayerDisplayWithTeamDTO> getPlayers() {
        return playerService.getAllPlayers().stream().map(player -> playerMapper.toDisplayDTOWithTeam(player)).collect(Collectors.toList());
    }

    @GetMapping("/players/team/id/{teamId}")
    public List<PlayerDisplayDTO> getPlayersByTeamId(@PathVariable int teamId) {
        return playerService.getPlayersByTeamId(teamId).stream().map(player -> playerMapper.toDisplayDTO(player)).collect(Collectors.toList());
    }

    @GetMapping("/players/team/name/{teamName}")
    public List<PlayerDisplayDTO> getPlayersByTeamName(@PathVariable String teamName) {
        return playerService.getPlayersByTeamName(teamName).stream().map(player -> playerMapper.toDisplayDTO(player)).collect(Collectors.toList());
    }


    @PostMapping("/player")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlayerResponseWithBody> addPlayer(@Valid @RequestBody PlayerRequestDTO playerDTO){

        PlayerResponseWithBody saveUserResponse = PlayerResponseWithBody.builder()
                .status(201)
                .message(SAVE_PLAYER_SUCCESS)
                .body(playerMapper.toDisplayDTO(playerService.savePlayer(playerDTO)))
                .build();



        return new ResponseEntity<>(saveUserResponse, HttpStatus.CREATED);
    }


    // WORK ON UPDATE
    @PutMapping("/player/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerResponseWithBody> updatePlayer(@PathVariable int playerId,@Valid @RequestBody PlayerRequestDTO playerDTO){

        PlayerResponseWithBody updateUserResponse = PlayerResponseWithBody.builder()
                .status(204)
                .message(UPDATE_PLAYER_SUCCESS)
                .body(playerMapper.toDisplayDTO(playerService.updatePlayer(playerId, playerDTO)))
                .build();

        return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
    }

    @DeleteMapping("/player/{playerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<PlayerResponse> deletePlayer(@NotNull @PathVariable("playerId") int playerId){

        playerService.deletePlayer(playerId);

        PlayerResponse deletePlayerResponse = PlayerResponse.builder()
                .status(202)
                .message(DELETE_PLAYER_SUCCESS)
                .build();

        return new ResponseEntity<>(deletePlayerResponse, HttpStatus.ACCEPTED);
    }

}
