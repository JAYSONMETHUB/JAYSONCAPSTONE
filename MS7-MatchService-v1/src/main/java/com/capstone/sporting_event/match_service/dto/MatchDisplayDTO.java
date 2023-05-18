package com.capstone.sporting_event.match_service.dto;

import com.capstone.sporting_event.match_service.models.Field;
import com.capstone.sporting_event.match_service.models.Team;
import com.capstone.sporting_event.match_service.models.Tournament;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class MatchDisplayDTO {

    private int matchId;

    private Field field;

    private Tournament tournament;

    private List<Team> teams;

    private Date dateTime;
}
