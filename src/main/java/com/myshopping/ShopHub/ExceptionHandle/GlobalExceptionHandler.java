package com.myshopping.ShopHub.ExceptionHandle;

import com.myshopping.ShopHub.Exception.LockedException;
import com.myshopping.ShopHub.Exception.UserAlreadyExist;
import com.myshopping.ShopHub.Exception.UserIsBlocked;
import com.myshopping.ShopHub.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<Map<String,String>> userExistError(UserAlreadyExist e){

        Map<String,String> map=new HashMap<>();
        map.put("message",e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);

    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String,String>> userNotExist(UsernameNotFoundException usernameNotFoundException){
        Map<String,String> map=new HashMap<>();
        map.put("Message",usernameNotFoundException.getLocalizedMessage());
        return
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(map);

    }
    @ExceptionHandler(UserIsBlocked.class)
    public ResponseEntity<Map<String,String>> userNotExist(UserIsBlocked userIsBlocked){
        Map<String,String> map=new HashMap<>();
        map.put("Message",userIsBlocked.getLocalizedMessage());
        return
                ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(map);

    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> userNotExist(UserNotFoundException exception){
        Map<String,String> map=new HashMap<>();
        map.put("Message",exception.getLocalizedMessage());
        return
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(map);

    }
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Map<String,String>> userIsLocked(LockedException exception){
        Map<String,String> map=new HashMap<>();
        map.put("Message",exception.getLocalizedMessage());
        return
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(map);

    }


}
