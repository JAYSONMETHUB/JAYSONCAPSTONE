package com.capstone.sporting_event.match_service.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.capstone.sporting_event.match_service.constants.MessageMap.*;

@ControllerAdvice

public class MatchExceptionHandler {
    @ExceptionHandler(value = MatchNotFoundException.class)
    public ResponseEntity<Object> handleTeamNotFoundException(MatchNotFoundException exception) {
        return new ResponseEntity<>(MATCH_NOT_FOUND, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = MatchAlreadyExistException.class)
    public ResponseEntity<Object> handleTeamAlreadyExistException(MatchAlreadyExistException exception) {
        return new ResponseEntity<>(MATCH_ALREADY_EXIST, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidMatchDetailsException.class)
    public ResponseEntity<Object> handleInvalidMatchDetailsException(InvalidMatchDetailsException exception) {
        return new ResponseEntity<>(INVALID_MATCH_DETAILS, HttpStatus.BAD_REQUEST);
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
