package com.loja.backendlojanelio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request){
        StandardError err = StandardError
                .builder()
                .status(HttpStatus.NOT_FOUND.value())
                .mensagem(error.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(err.getStatus()).body(err);
    }
}
