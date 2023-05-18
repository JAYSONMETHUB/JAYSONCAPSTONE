package com.capstone.sporting_event.field_service;


import com.capstone.sporting_event.field_service.dto.FieldDisplayDTO;
import com.capstone.sporting_event.field_service.dto.FieldMapper;
import com.capstone.sporting_event.field_service.dto.FieldRequestDTO;
import com.capstone.sporting_event.field_service.entity.Field;
import com.capstone.sporting_event.field_service.exceptions.FieldNotFoundException;
import com.capstone.sporting_event.field_service.repository.FieldRepository;
import com.capstone.sporting_event.field_service.service.FieldServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FieldServiceTests {

    @Mock
    private FieldRepository fieldRepo;

    @Mock
    private FieldMapper fieldMapper;

    @InjectMocks
    private FieldServiceImpl fieldService;



    private Field fieldEntity;


    @BeforeEach
    public void setup(){

        fieldEntity = Field.builder()
                .fieldName("jayson")
                .fieldId(1)
                .build();
    }



    @Test
    void should_Be_Able_To_Get_Field()
    {
        FieldDisplayDTO expected = FieldDisplayDTO.builder()
                .fieldName("jayson")
                .fieldId(1).build();

        when(fieldRepo.findById(anyInt())).thenReturn(Optional.ofNullable(fieldEntity));
        when(fieldMapper.toDisplayDTO(any(Field.class))).thenReturn(expected);

        FieldDisplayDTO fieldDTO = fieldMapper.toDisplayDTO(fieldService.getField(1));

        assertNotNull(fieldDTO);
        assertEquals(expected.getFieldName(), fieldDTO.getFieldName());
        


    }


    @Test
    void should_Be_Able_To_Get_Field_ByName()
    {
        FieldDisplayDTO expected = FieldDisplayDTO.builder()
                .fieldName("jayson")
                .fieldId(1).build();

        when(fieldRepo.findByFieldNameIs(anyString())).thenReturn(Optional.ofNullable(fieldEntity));
        when(fieldMapper.toDisplayDTO(any(Field.class))).thenReturn(expected);

        FieldDisplayDTO fieldDTO = fieldMapper.toDisplayDTO(fieldService.getFieldByName("jayson"));


        assertNotNull(fieldDTO);
        assertEquals(expected.getFieldName(), fieldDTO.getFieldName());

    }
    @Test
    void should_Be_Able_To_Get_All_Field() {
        FieldRequestDTO fieldRequest = FieldRequestDTO.builder()
                .fieldName("jayson")
                .build();

        when(fieldRepo.findAll()).thenReturn(List.of(fieldEntity));

        List<Field> fields = fieldService.getAllFields();

        assertEquals(1,fields.size());
        assertEquals(fieldEntity,fields.get(0));


    }


    @Test
    void should_Throw_Field_Not_Found_Exception()
    {

       when(fieldRepo.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(FieldNotFoundException.class, () -> {

            fieldService.getField(1);

        });
    }

    @Test
    void should_Be_Able_To_Save_Field()
    {
       Field expected = fieldEntity;

        Field newfield = Field.builder()
                .fieldId(1)
                .fieldName("testname")
                .build();

        FieldRequestDTO fieldRequest = FieldRequestDTO.builder()
                .fieldName("jayson")
                .build();


        when(fieldRepo.save(any(Field.class))).thenReturn(fieldEntity);
        when(fieldMapper.toEntity(any(FieldRequestDTO.class))).thenReturn(fieldEntity);


        Field savedField = fieldService.saveField(fieldRequest);

        assertNotNull(savedField);
        assertEquals(expected.getFieldName(), fieldRequest.getFieldName());
 

    }



    @Test
    void should_Be_Able_To_Update_Field() {
        FieldRequestDTO fieldRequest = FieldRequestDTO.builder()
                .fieldName("jayson")
                .build();

        Field newfield = Field.builder()
                .fieldId(1)
                .fieldName("testname")
                .build();

        Field expectedField = Field.builder()
                .fieldName("updatedField")
                .build();

       // when(fieldClient.getFieldByName(fieldRequest.getFieldName())).thenReturn(newfield);
        when(fieldRepo.findById(1)).thenReturn(Optional.ofNullable(fieldEntity));
        when(fieldRepo.save(any(Field.class))).thenReturn(expectedField);

        Field actualField = fieldService.updateField(1,fieldRequest);


        assertNotNull(actualField);

        assertEquals(expectedField.getFieldName(),actualField.getFieldName());



    }



    @Test
    void should_Be_Able_To_Delete_Field() {
        FieldRequestDTO fieldRequest = FieldRequestDTO.builder()
                .fieldName("jayson")
                .build();


        when(fieldRepo.findById(1)).thenReturn(Optional.ofNullable(fieldEntity));

        fieldService.deleteField(1);

        verify(fieldRepo).save(any(Field.class));


    }

    @Test
    void should_Throw_Field_Not_Found_In_Delete_Field() {


        when(fieldRepo.findById(1)).thenReturn(Optional.empty());


        Exception exception = assertThrows(FieldNotFoundException.class, () -> {

            fieldService.deleteField(1);

        });

        String expectedMessage = "com.capstone.sporting_event.field_service.exceptions.FieldNotFoundException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }



    }


