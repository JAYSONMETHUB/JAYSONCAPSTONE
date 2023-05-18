package com.capstone.sporting_event.field_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldDisplayDTO {

    private int fieldId;

    private String fieldName;
    private String fieldAddress;
    private int capacity;
}
