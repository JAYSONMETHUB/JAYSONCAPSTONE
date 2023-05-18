package com.capstone.sporting_event.tournament_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentDisplayDTO {

    private int tournamentId;

    private String tournamentName;
    private String sportsCategory;
    private String tournamentStyle;
}
