package com.capstone.sporting_event.field_service.dto;


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
public class FieldRequestDTO {

    @NotNull(message="Field name should not be null")
    @NotBlank(message="Field name should not be blank")
    @NotEmpty(message="Field name should not be empty")
    @Size(min=6,message="Field name should be at least 2 characters long")
    private String fieldName;


    @NotNull(message="Field address should not be null")
    @NotBlank(message="Field address should not be blank")
    @NotEmpty(message="Field AA address not be empty")
    @Size(min=2,message="Field address should be at least 2 characters long")
    private String fieldAddress;

    @NotNull(message="Field capacity should not be null")
    private int capacity;
}
