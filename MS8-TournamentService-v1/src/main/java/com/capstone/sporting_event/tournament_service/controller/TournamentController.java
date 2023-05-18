package com.capstone.sporting_event.tournament_service.controller;

import com.capstone.sporting_event.tournament_service.dto.TournamentDisplayDTO;
import com.capstone.sporting_event.tournament_service.dto.TournamentMapper;
import com.capstone.sporting_event.tournament_service.dto.TournamentRequestDTO;
import com.capstone.sporting_event.tournament_service.models.TournamentResponse;
import com.capstone.sporting_event.tournament_service.models.TournamentResponseWithBody;
import com.capstone.sporting_event.tournament_service.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.tournament_service.constants.Mapping.VERSION_1;
import static com.capstone.sporting_event.tournament_service.constants.MessageMap.*;


@RestController
@RequestMapping(value=VERSION_1)
public class TournamentController {
    
    @Autowired
    TournamentService tournamentService;
    @Autowired
    TournamentMapper tournamentMapper;

    @GetMapping("/tournament/id/{tournamentId}")
    public TournamentDisplayDTO getTournamentById(@PathVariable int tournamentId) {
        return tournamentMapper.toDisplayDTO(tournamentService.getTournament(tournamentId));
    }

    @GetMapping("/tournament/name/{tournamentName}")
    public TournamentDisplayDTO getTournamentByName(@PathVariable String tournamentName) {
        return tournamentMapper.toDisplayDTO(tournamentService.getTournamentByName(tournamentName));
    }

    @GetMapping("/tournaments")
    public List<TournamentDisplayDTO> getTournaments() {
        return tournamentService.getAllTournaments().stream().map(user -> tournamentMapper.toDisplayDTO(user)).collect(Collectors.toList());
    }


    @PostMapping("/tournament")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TournamentResponseWithBody> addTournament(@RequestBody TournamentRequestDTO tournamentDTO){

        TournamentResponseWithBody saveTournamentResponse = TournamentResponseWithBody.builder()
                .status(201)
                .message(SAVE_TOURNAMENT_SUCCESS)
                .body(tournamentMapper.toDisplayDTO(tournamentService.saveTournament(tournamentDTO)))
                .build();



        return new ResponseEntity<>(saveTournamentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/tournament/{tournamentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TournamentResponseWithBody> updateTournament(@PathVariable int tournamentId, @Valid @RequestBody TournamentRequestDTO tournamentRequestDTO){

        TournamentResponseWithBody updateTournamentResponse = TournamentResponseWithBody.builder()
                .status(204)
                .message(UPDATE_TOURNAMENT_SUCCESS)
                .body(tournamentMapper.toDisplayDTO(tournamentService.updateTournament(tournamentId, tournamentRequestDTO)))
                .build();

        return new ResponseEntity<>(updateTournamentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/tournament/{tournamentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<TournamentResponse> deleteTournament(@PathVariable("tournamentId") int tournamentId){

        tournamentService.deleteTournament(tournamentId);

        TournamentResponse deleteTournamentResponse = TournamentResponse.builder()
                .status(202)
                .message(DELETE_TOURNAMENT_SUCCESS)
                .build();

        return new ResponseEntity<>(deleteTournamentResponse, HttpStatus.ACCEPTED);
    }

}
