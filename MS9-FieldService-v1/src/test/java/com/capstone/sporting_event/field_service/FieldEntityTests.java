package com.capstone.sporting_event.field_service;


import com.capstone.sporting_event.field_service.entity.Field;
import com.capstone.sporting_event.field_service.repository.FieldRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)

class FieldEntityTests {


    int id = 1;
    String fieldName = "jayson";

    String fieldAddress = "sample address";
    int capacity = 100;

    boolean active = true;

    @Autowired
    private FieldRepository fieldRepo;


    // TEST GETTERS,SETTERS

    @Test
    void test_Getters_And_Setters(){


        Field field = new Field();

        field.setFieldId(id);
        field.setFieldName(fieldName);
        field.setFieldAddress(fieldAddress);
        field.setCapacity(capacity);
        field.setActive(active);
       
      

        String toString = field.toString();

        assertEquals(field.getFieldId(),id);
        assertEquals(field.getFieldName(),fieldName);
        assertEquals(field.getFieldAddress(),fieldAddress);
        assertEquals(field.getCapacity(),capacity);
        assertEquals(field.isActive(),active);
        assertNotNull(toString);

    }

    @Test
    void test_Builder(){


        Field field = Field.builder()
                .fieldId(id)
                .fieldName(fieldName)
                .fieldAddress(fieldAddress)
                .capacity(capacity)
                .isActive(active)
                .build();

        String toString = field.toString();
        assertNotNull(toString);

        assertEquals(field.getFieldId(),id);
        assertEquals(field.getFieldName(),fieldName);
        assertEquals(field.getFieldAddress(),fieldAddress);
        assertEquals(field.getCapacity(),capacity);
        assertEquals(field.isActive(),active);

    }



    //TEST CONSTRAINTS

    @Test
    void should_Throw_Exception_If_Field_Name_Is_Empty(){
            Field field = Field.builder()
                    .fieldName(null)
                    .fieldAddress(fieldAddress)
                    .capacity(capacity)
                    .fieldId(1)
                    .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            fieldRepo.saveAndFlush(field);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [field_name\" of relation \"field]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void should_Throw_Exception_If_Field_Address_Is_Empty(){
        Field field = Field.builder()
                .fieldName(fieldName)
                .fieldAddress(null)
                .capacity(capacity)
                .fieldId(1)
                .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            fieldRepo.saveAndFlush(field);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [field_address\" of relation \"field]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }





}
