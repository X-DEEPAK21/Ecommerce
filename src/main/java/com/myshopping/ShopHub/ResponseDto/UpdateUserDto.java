package com.myshopping.ShopHub.ResponseDto;

public class UpdateUserDto {

    String full_name;
    String contact_number;

    public UpdateUserDto() {
    }

    public UpdateUserDto(String full_name, String contact_number) {
        this.full_name = full_name;
        this.contact_number = contact_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}
