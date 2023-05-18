package com.capstone.sporting_event.team_service.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.capstone.sporting_event.team_service.constants.MessageMap.TEAM_ALREADY_EXIST;
import static com.capstone.sporting_event.team_service.constants.MessageMap.TEAM_NOT_FOUND;

@ControllerAdvice

public class TeamExceptionHandler {
    @ExceptionHandler(value = TeamNotFoundException.class)
    public ResponseEntity<Object> handleTeamNotFoundException(TeamNotFoundException exception) {
        return new ResponseEntity<>(TEAM_NOT_FOUND, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = TeamAlreadyExistException.class)
    public ResponseEntity<Object> handleTeamAlreadyExistException(TeamAlreadyExistException exception) {
        return new ResponseEntity<>(TEAM_ALREADY_EXIST, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;

    }
}
