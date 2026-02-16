package com.myshopping.ShopHub.Exception;

public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(String msg){
        super(msg);
    }
}
