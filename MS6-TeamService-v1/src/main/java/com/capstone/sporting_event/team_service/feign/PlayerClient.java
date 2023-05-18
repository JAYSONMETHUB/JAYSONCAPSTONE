package com.capstone.sporting_event.team_service.feign;


import com.capstone.sporting_event.team_service.config.FeignClientConfiguration;
import com.capstone.sporting_event.team_service.models.Player;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MS5-PlayerService-v1", configuration = FeignClientConfiguration.class)
public interface PlayerClient {
    @GetMapping(value = "/ms5/api/v1/players/team/name/{teamName}")
    List<Player> getPlayerByTeamName(@PathVariable("teamName") String teamName);

    @GetMapping(value = "/ms5/api/v1/players/team/id/{teamId}")
    List<Player> getPlayersByTeamId(@PathVariable("teamId") int teamId);
}
