package com.capstone.sporting_event.tournament_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentResponse {
    int status;
    String message;
}
