package com.myshopping.ShopHub.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AppUsers{
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long userid;
   @Email
   @Column(unique = true,nullable = false)
   @NotBlank(message = "Email cannot be blank")
   private String email;
    /* @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$",
            message = "Password must contain at least one letter and one number"
    )*/
    @Size(min = 5)
   private String password;
   @NotBlank(message = "name cannot be blank")
   private String full_name;
   @NotBlank(message = "Phone number Cannot be blank")
   @Size(min = 10,max = 10)
   private String phone_number;
   @Enumerated(EnumType.STRING)
   private Role role;
   @Column(nullable = false)
   private Boolean is_active=true;
   private String created_by;
   private String updated_by;
   private LocalDateTime created_at;
   private LocalDateTime updated_at;



    public AppUsers(String email, String password, String full_name, String phone_number, Role role, Boolean is_active) {
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.role = role;
        this.is_active = is_active;
    }
    @PrePersist
    public void onCreation(){

        this.created_at=LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate(){

        this.updated_at=LocalDateTime.now();
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
