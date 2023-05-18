package com.capstone.sporting_event.field_service.service;

import com.capstone.sporting_event.field_service.dto.FieldRequestDTO;
import com.capstone.sporting_event.field_service.entity.Field;
import com.capstone.sporting_event.field_service.exceptions.FieldAlreadyExistException;
import com.capstone.sporting_event.field_service.exceptions.FieldNotFoundException;

import java.util.List;

public interface FieldService {
    public Field saveField(FieldRequestDTO fieldDTO) throws FieldAlreadyExistException;

    public List<Field> getAllFields();
    public Field getField(int id) throws FieldNotFoundException;
    public Field getFieldByName(String name) throws FieldNotFoundException;

    public Field updateField(int id, FieldRequestDTO fieldDTO) throws FieldNotFoundException;

    public void deleteField(int id) throws FieldNotFoundException;

}
