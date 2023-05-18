package com.capstone.sporting_event.match_service.controller;

import com.capstone.sporting_event.match_service.dto.MatchDisplayDTO;
import com.capstone.sporting_event.match_service.dto.MatchMapper;
import com.capstone.sporting_event.match_service.dto.MatchRequestDTO;
import com.capstone.sporting_event.match_service.models.MatchResponse;
import com.capstone.sporting_event.match_service.models.MatchResponseWithBody;
import com.capstone.sporting_event.match_service.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.match_service.constants.Mapping.VERSION_1;
import static com.capstone.sporting_event.match_service.constants.MessageMap.*;


@RestController
@RequestMapping(value=VERSION_1)
public class MatchController {
    
    @Autowired
    MatchService matchService;
    @Autowired
    MatchMapper matchMapper;

    @GetMapping("/match/id/{matchId}")
    public MatchDisplayDTO getMatchById(@PathVariable int matchId) {
        return matchMapper.toDisplayDTO(matchService.getMatch(matchId));
    }

    @GetMapping("/matches")
    public List<MatchDisplayDTO> getMatches() {
        return matchService.getAllMatchs().stream().map(user -> matchMapper.toDisplayDTO(user)).collect(Collectors.toList());
    }


    @PostMapping("/match")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MatchResponseWithBody> addMatch(@RequestBody MatchRequestDTO matchDTO){

        MatchResponseWithBody saveMatchResponse = MatchResponseWithBody.builder()
                .status(201)
                .message(SAVE_MATCH_SUCCESS)
                .body(matchMapper.toDisplayDTO(matchService.saveMatch(matchDTO)))
                .build();



        return new ResponseEntity<>(saveMatchResponse, HttpStatus.CREATED);
    }

    @PutMapping("/match/{matchId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MatchResponseWithBody> updateMatch(@PathVariable int matchId, @Valid @RequestBody MatchRequestDTO matchRequestDTO){

        MatchResponseWithBody updateMatchResponse = MatchResponseWithBody.builder()
                .status(204)
                .message(UPDATE_MATCH_SUCCESS)
                .body(matchMapper.toDisplayDTO(matchService.updateMatch(matchId, matchRequestDTO)))
                .build();

        return new ResponseEntity<>(updateMatchResponse, HttpStatus.OK);
    }

    @DeleteMapping("/match/{matchId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<MatchResponse> deleteMatch(@PathVariable("matchId") int matchId){

        matchService.deleteMatch(matchId);

        MatchResponse deleteMatchResponse = MatchResponse.builder()
                .status(202)
                .message(DELETE_MATCH_SUCCESS)
                .build();

        return new ResponseEntity<>(deleteMatchResponse, HttpStatus.ACCEPTED);
    }

}
