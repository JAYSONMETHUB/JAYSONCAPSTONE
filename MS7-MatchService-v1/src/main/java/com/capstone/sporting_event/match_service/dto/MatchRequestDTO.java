package com.capstone.sporting_event.match_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequestDTO {

    @NotNull(message="Field id should not be null")
    private int fieldId;

    @NotNull(message="Tournament id id should not be null")
    private int tournamentId;

    @NotNull(message="Teams ID should not be null")
    private int[] teamsId;

    @NotNull(message="Sports Category should not be null")
    @DateTimeFormat
    private Date dateTime;

}
