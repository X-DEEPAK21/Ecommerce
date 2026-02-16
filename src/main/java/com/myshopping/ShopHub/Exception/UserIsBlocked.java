package com.myshopping.ShopHub.Exception;

public class UserIsBlocked extends RuntimeException{
    public UserIsBlocked(String msg){
        super(msg);
    }
}
