package com.capstone.sporting_event.team_service.models;

import com.capstone.sporting_event.team_service.dto.TeamDisplayDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponseWithBody
{

    int status;
    String message;
    TeamDisplayDTO body;
}
