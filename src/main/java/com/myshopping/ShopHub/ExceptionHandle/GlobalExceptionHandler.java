package com.myshopping.ShopHub.ExceptionHandle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationError(MethodArgumentNotValidException exception){
        Map<String,String> map=new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach((ex)->{
                    map.put(ex.getField(), exception.getLocalizedMessage());
                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);


    }

}
