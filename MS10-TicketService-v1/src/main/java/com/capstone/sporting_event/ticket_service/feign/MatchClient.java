package com.capstone.sporting_event.ticket_service.feign;

import com.capstone.sporting_event.ticket_service.config.FeignClientConfiguration;
import com.capstone.sporting_event.ticket_service.models.Match;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "MS7-MatchService-v1", configuration = FeignClientConfiguration.class)
public interface MatchClient {


    @GetMapping(value = "ms7/api/v1/match/id/{matchId}")
    Match getTeamById(@PathVariable("matchId") int matchId);
}
