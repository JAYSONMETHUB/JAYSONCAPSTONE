package com.capstone.sporting_event.field_service.models;

import com.capstone.sporting_event.field_service.dto.FieldDisplayDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldResponseWithBody
{

    int status;
    String message;
    FieldDisplayDTO body;
}
