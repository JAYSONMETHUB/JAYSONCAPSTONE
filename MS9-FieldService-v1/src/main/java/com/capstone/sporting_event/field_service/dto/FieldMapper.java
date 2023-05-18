package com.capstone.sporting_event.field_service.dto;

import com.capstone.sporting_event.field_service.entity.Field;


public class FieldMapper {


    public FieldRequestDTO toRequestDTO(Field entity){

        return FieldRequestDTO.builder()
                .fieldName(entity.getFieldName())
                .fieldAddress(entity.getFieldAddress())
                .capacity(entity.getCapacity())
                .build();
    }

    public FieldDisplayDTO toDisplayDTO(Field entity){


        return FieldDisplayDTO.builder()
                .fieldId(entity.getFieldId())
                .fieldName(entity.getFieldName())
                .fieldAddress(entity.getFieldAddress())
                .capacity(entity.getCapacity())
                .build();
    }



    public Field toEntity(FieldRequestDTO dto){

        return Field.builder()
                .fieldName(dto.getFieldName())
                .fieldAddress(dto.getFieldAddress())
                .capacity(dto.getCapacity())
                .build();
    }



}
