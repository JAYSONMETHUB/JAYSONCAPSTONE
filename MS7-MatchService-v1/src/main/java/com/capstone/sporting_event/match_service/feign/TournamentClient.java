package com.capstone.sporting_event.match_service.feign;

import com.capstone.sporting_event.match_service.config.FeignClientConfiguration;
import com.capstone.sporting_event.match_service.models.Team;
import com.capstone.sporting_event.match_service.models.Tournament;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "MS8-TournamentService-v1" , configuration = FeignClientConfiguration.class)
public interface TournamentClient {

    @GetMapping(value = "ms8/api/v1/tournament/name/{tournamentName}")
    Tournament getTournamentByName(@PathVariable("tournamentName") String tournamentName);

    @GetMapping(value = "ms8/api/v1/tournament/id/{tournamentId}")
    Tournament getTournamentById(@PathVariable("tournamentId") int tournamentId);


}
