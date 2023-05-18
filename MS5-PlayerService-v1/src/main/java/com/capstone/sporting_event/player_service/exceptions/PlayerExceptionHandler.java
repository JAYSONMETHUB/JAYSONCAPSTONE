package com.capstone.sporting_event.player_service.exceptions;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.player_service.constants.MessageMap.PLAYER_ALREADY_EXIST;
import static com.capstone.sporting_event.player_service.constants.MessageMap.PLAYER_NOT_FOUND;

@ControllerAdvice

public class PlayerExceptionHandler {
    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseEntity<Object> handlePlayerNotFoundException(PlayerNotFoundException exception) {
        return new ResponseEntity<>(PLAYER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PlayerAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(PlayerAlreadyExistsException exception) {
        return new ResponseEntity<>(PLAYER_ALREADY_EXIST, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
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
