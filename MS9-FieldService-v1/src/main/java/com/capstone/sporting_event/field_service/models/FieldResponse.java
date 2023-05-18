package com.capstone.sporting_event.field_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldResponse {
    int status;
    String message;
}
