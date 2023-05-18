package com.capstone.sporting_event.tournament_service.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.capstone.sporting_event.tournament_service.constants.MessageMap.TOURNAMENT_ALREADY_EXIST;
import static com.capstone.sporting_event.tournament_service.constants.MessageMap.TOURNAMENT_NOT_FOUND;

@ControllerAdvice

public class TournamentExceptionHandler {
    @ExceptionHandler(value = TournamentNotFoundException.class)
    public ResponseEntity<Object> handleTeamNotFoundException(TournamentNotFoundException exception) {
        return new ResponseEntity<>(TOURNAMENT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = TournamentAlreadyExistException.class)
    public ResponseEntity<Object> handleTeamAlreadyExistException(TournamentAlreadyExistException exception) {
        return new ResponseEntity<>(TOURNAMENT_ALREADY_EXIST, HttpStatus.CONFLICT);
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
