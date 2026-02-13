package com.myshopping.ShopHub.ResponseDto;

public class RegistrationResponseDto {
    String full_name;
    String email;
    String contact_number;

    public RegistrationResponseDto() {
    }

    public RegistrationResponseDto(String contact_number, String full_name, String email) {
        this.contact_number = contact_number;
        this.full_name = full_name;
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}
