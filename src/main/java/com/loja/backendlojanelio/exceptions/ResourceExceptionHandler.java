package com.loja.backendlojanelio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException error, HttpServletRequest request){
        StandardError err = StandardError
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(error.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> dataIntegrity(MethodArgumentNotValidException error, HttpServletRequest request){
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());

        err.setErrors(error
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> new FieldMessage(field.getField(), field.getDefaultMessage()))
                .collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
