package com.capstone.sporting_event.user_service.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;


@Data
@Builder
public class UserRequestDTO {


    @NotNull(message="Username should not be null")
    @NotBlank(message="Username should not be blank")
    @NotEmpty(message="Username should not be empty")
    @Size(min=6,message="Username should be at least 6 characters long")
    private String userName;

    @NotNull(message="Email should not be null")
    @NotBlank(message="Email should not be blank")
    @NotEmpty(message="Email should not be empty")
    @Email(message = "Email is not Valid")
    private String email;

    @NotNull(message="Password name should not be null")
    @NotBlank(message="Password name should not be blank")
    @NotEmpty(message="Password name should not be empty")
    @Size(min=8,message="Password should be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Password should contain an one uppercase, a number, and a special character")
    private String password;

}
