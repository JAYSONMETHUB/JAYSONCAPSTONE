package com.capstone.sporting_event.team_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDisplayDTO {

    private int teamId;

    private String teamName;
}
