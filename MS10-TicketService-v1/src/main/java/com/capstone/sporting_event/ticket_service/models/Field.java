package com.capstone.sporting_event.ticket_service.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Field {

    private int fieldId;
    private String fieldName;
    private String fieldAddress;
    private int capacity;


}
