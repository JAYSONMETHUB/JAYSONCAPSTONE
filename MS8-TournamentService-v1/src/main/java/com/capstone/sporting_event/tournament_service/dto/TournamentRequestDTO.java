package com.capstone.sporting_event.tournament_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentRequestDTO {

    @NotNull(message="Tournament name should not be null")
    @NotBlank(message="Tournament name should not be blank")
    @NotEmpty(message="Tournament name should not be empty")
    @Size(min=2,message="Tournament name should be at least 2 characters long")
    private String tournamentName;


    @NotNull(message="Sports Category should not be null")
    @NotBlank(message="Sports Category should not be blank")
    @NotEmpty(message="Sports Category address not be empty")
    @Size(min=6,message="Sports Category should be at least 6 characters long")
    private String sportsCategory;


    @NotNull(message="Tournament style should not be null")
    @NotBlank(message="Tournament style should not be blank")
    @NotEmpty(message="Tournament style address not be empty")
    @Size(min=4,message="Tournament style should be at least 4 characters long")
    private String tournamentStyle;
}
