package com.capstone.sporting_event.team_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {


    private int playerId;

    private String firstName;

    private String lastName;

    private String country;

}
