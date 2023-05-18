package com.capstone.sporting_event.field_service.service;

import com.capstone.sporting_event.field_service.dto.FieldMapper;
import com.capstone.sporting_event.field_service.dto.FieldRequestDTO;
import com.capstone.sporting_event.field_service.entity.Field;
import com.capstone.sporting_event.field_service.exceptions.FieldAlreadyExistException;
import com.capstone.sporting_event.field_service.exceptions.FieldNotFoundException;
import com.capstone.sporting_event.field_service.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    FieldRepository fieldRepo;

    @Autowired
    FieldMapper fieldMapper;

    public Field saveField(FieldRequestDTO fieldDTO){

        if(fieldRepo.findByFieldNameIs(fieldDTO.getFieldName()).isPresent())
            throw new FieldAlreadyExistException();

       return fieldRepo.save(fieldMapper.toEntity(fieldDTO));
    }


    public List<Field> getAllFields() {
        return fieldRepo.findAll();
    }



    public Field getField(int id) {

        Optional<Field> field = fieldRepo.findById(id);

        if(field.isEmpty()){
            throw new FieldNotFoundException();
        }

        return field.get();
    }

    public Field getFieldByName(String fieldName){
        Optional<Field> field = fieldRepo.findByFieldNameIs(fieldName);

        if(field.isEmpty()){
            throw new FieldNotFoundException();
        }

        return field.get();
    }


    public Field updateField(int id, FieldRequestDTO fieldDTO){

        Optional<Field> field = fieldRepo.findById(id);

        if(field.isEmpty()){
            throw new FieldNotFoundException();
        }

        Field updatedField = field.get();

        updatedField.setFieldAddress(fieldDTO.getFieldAddress());
        updatedField.setFieldName(fieldDTO.getFieldName());
        updatedField.setCapacity(fieldDTO.getCapacity());

        return fieldRepo.save(updatedField);
    }

    public void deleteField(int id) {
        Optional<Field> field = fieldRepo.findById(id);

        if(field.isEmpty()) {
            throw new FieldNotFoundException();
        }

        Field deletedField = field.get();

        deletedField.setActive(false);
        fieldRepo.save(deletedField);
    }


}
