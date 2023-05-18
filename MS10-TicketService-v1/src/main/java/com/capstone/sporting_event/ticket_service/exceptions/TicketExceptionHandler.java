package com.capstone.sporting_event.ticket_service.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.capstone.sporting_event.ticket_service.constants.MessageMap.*;

@ControllerAdvice

public class TicketExceptionHandler {
    @ExceptionHandler(value = TicketNotFoundException.class)
    public ResponseEntity<Object> handleTeamNotFoundException(TicketNotFoundException exception) {
        return new ResponseEntity<>(TICKET_NOT_FOUND, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = TicketAlreadyExistException.class)
    public ResponseEntity<Object> handleTeamAlreadyExistException(TicketAlreadyExistException exception) {
        return new ResponseEntity<>(TICKET_ALREADY_EXIST, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidMatchIdException.class)
    public ResponseEntity<Object> handleInvalidMatchIdException(InvalidMatchIdException exception) {
        return new ResponseEntity<>(INVALID_MATCH_ID, HttpStatus.BAD_REQUEST);
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
