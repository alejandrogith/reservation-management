package com.example.ReservationManagement.Shared.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {


    record ResponseValidation(Map<String, String> errors){}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseValidation>
    handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });


        var Response=new ResponseValidation(errors);

        return new ResponseEntity<ResponseValidation>(Response, HttpStatus.BAD_REQUEST);
    }
}