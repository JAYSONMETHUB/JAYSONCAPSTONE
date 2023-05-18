package com.capstone.sporting_event.team_service.dto;


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
public class TeamRequestDTO {

    @NotNull(message="Team name should not be null")
    @NotBlank(message="Team name should not be blank")
    @NotEmpty(message="Team name should not be empty")
    @Size(min=2,message="Team name should be at least 2 characters long")
    private String teamName;
}
