package com.capstone.sporting_event.player_service.dto;


import lombok.Data;
import lombok.Builder;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class PlayerRequestDTO {



    @NotNull(message="First name should not be null")
    @NotBlank(message="First name should not be blank")
    @NotEmpty(message="First name should not be empty")
    @Size(min=2,message="First name should be at least 2 characters long")
    private String firstName;

    @NotNull(message="Last name should not be null")
    @NotBlank(message="Last name should not be blank")
    @NotEmpty(message="Last name should not be empty")
    @Size(min=2,message="Last name should be at least 2 characters long")
    private String lastName;

    @NotNull(message="Country name should not be null")
    @NotBlank(message="Country name should not be blank")
    @NotEmpty(message="Country name should not be empty")
    @Size(min = 4,message="Country should be at least 4 characters long")
    private String country;

    private String teamName;



}
