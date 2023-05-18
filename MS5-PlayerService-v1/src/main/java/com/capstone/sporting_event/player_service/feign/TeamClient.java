package com.capstone.sporting_event.player_service.feign;

import com.capstone.sporting_event.player_service.config.FeignClientConfiguration;
import com.capstone.sporting_event.player_service.models.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "MS6-TeamService-v1", configuration = FeignClientConfiguration.class)
public interface TeamClient {

    @GetMapping(value = "/ms6/api/v1/team/name/{teamName}")
    Team getTeamByName(@PathVariable("teamName") String teamName);

    @GetMapping(value = "/ms6/api/v1/team/id/{teamId}")
    Team getTeamById(@PathVariable("teamId") int teamId);

}
