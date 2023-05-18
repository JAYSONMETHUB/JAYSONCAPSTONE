package com.capstone.sporting_event.tournament_service.models;

import com.capstone.sporting_event.tournament_service.dto.TournamentDisplayDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentResponseWithBody
{

    int status;
    String message;
    TournamentDisplayDTO body;
}
