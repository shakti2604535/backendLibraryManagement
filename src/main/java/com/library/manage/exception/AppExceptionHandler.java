package com.library.manage.exception;

//package com.library.manage.exception;
//
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleWrongArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorResp = new HashMap<>();
       
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorResp.put(error.getField() , error.getDefaultMessage());
            System.out.println("hi");
        });
        
        ex.getBindingResult().getGlobalErrors()  .forEach(error -> {
            errorResp.put(error.getCode(), error.getDefaultMessage());
            System.out.println("hi");
        });
        return errorResp;
    }
}
