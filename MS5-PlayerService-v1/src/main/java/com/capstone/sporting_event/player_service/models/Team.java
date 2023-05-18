package com.capstone.sporting_event.player_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Team {

     int teamId;
     String teamName;

}
