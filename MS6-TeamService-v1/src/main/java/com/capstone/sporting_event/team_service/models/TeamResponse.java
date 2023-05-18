package com.capstone.sporting_event.team_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponse {
    int status;
    String message;
}
