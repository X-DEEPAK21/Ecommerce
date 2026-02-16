package com.myshopping.ShopHub.ResponseDto;

public class LoginResponseDto {
    String email;
    String full_name;
    String phone_number;
    String token;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String email, String full_name, String phone_number, String token) {
        this.email = email;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
