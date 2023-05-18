package com.capstone.sporting_event.field_service.controller;

import com.capstone.sporting_event.field_service.dto.FieldDisplayDTO;
import com.capstone.sporting_event.field_service.dto.FieldMapper;
import com.capstone.sporting_event.field_service.dto.FieldRequestDTO;
import com.capstone.sporting_event.field_service.models.FieldResponse;
import com.capstone.sporting_event.field_service.models.FieldResponseWithBody;
import com.capstone.sporting_event.field_service.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.field_service.constants.Mapping.VERSION_1;
import static com.capstone.sporting_event.field_service.constants.MessageMap.*;


@RestController
@RequestMapping(value=VERSION_1)
public class FieldController {
    
    @Autowired
    FieldService fieldService;
    @Autowired
    FieldMapper fieldMapper;

    @GetMapping("/field/id/{fieldId}")
    public FieldDisplayDTO getFieldById(@PathVariable int fieldId) {
        return fieldMapper.toDisplayDTO(fieldService.getField(fieldId));
    }

    @GetMapping("/field/name/{fieldName}")
    public FieldDisplayDTO getFieldByName(@PathVariable String fieldName) {
        return fieldMapper.toDisplayDTO(fieldService.getFieldByName(fieldName));
    }

    @GetMapping("/fields")
    public List<FieldDisplayDTO> getFields() {
        return fieldService.getAllFields().stream().map(user -> fieldMapper.toDisplayDTO(user)).collect(Collectors.toList());
    }


    @PostMapping("/field")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FieldResponseWithBody> addField(@RequestBody FieldRequestDTO fieldDTO){

        FieldResponseWithBody saveFieldResponse = FieldResponseWithBody.builder()
                .status(201)
                .message(SAVE_FIELD_SUCCESS)
                .body(fieldMapper.toDisplayDTO(fieldService.saveField(fieldDTO)))
                .build();



        return new ResponseEntity<>(saveFieldResponse, HttpStatus.CREATED);
    }

    @PutMapping("/field/{fieldId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FieldResponseWithBody> updateField(@PathVariable int fieldId, @Valid @RequestBody FieldRequestDTO fieldRequestDTO){

        FieldResponseWithBody updateFieldResponse = FieldResponseWithBody.builder()
                .status(204)
                .message(UPDATE_FIELD_SUCCESS)
                .body(fieldMapper.toDisplayDTO(fieldService.updateField(fieldId, fieldRequestDTO)))
                .build();

        return new ResponseEntity<>(updateFieldResponse, HttpStatus.OK);
    }

    @DeleteMapping("/field/{fieldId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<FieldResponse> deleteField(@PathVariable("fieldId") int fieldId){

        fieldService.deleteField(fieldId);

        FieldResponse deleteFieldResponse = FieldResponse.builder()
                .status(202)
                .message(DELETE_FIELD_SUCCESS)
                .build();

        return new ResponseEntity<>(deleteFieldResponse, HttpStatus.ACCEPTED);
    }

}
