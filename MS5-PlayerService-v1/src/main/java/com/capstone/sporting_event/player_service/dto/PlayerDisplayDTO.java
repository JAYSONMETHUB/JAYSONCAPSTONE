package com.capstone.sporting_event.player_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDisplayDTO {

    private int playerId;

    private String firstName;

    private String lastName;

    private String country;

    private Integer teamId;

}
