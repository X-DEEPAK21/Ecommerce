package com.myshopping.ShopHub.Controller;


import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Entity.Role;
import com.myshopping.ShopHub.RequestDtos.RegistrationRequestDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.Service.UserService;
import com.myshopping.ShopHub.mapper.RegistrationRequestMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app/user")
public class UserController {

    @Autowired
    RegistrationRequestMapper registrationRequestMapper;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> saveUser(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){

        AppUsers appUser=registrationRequestMapper.toEntity(registrationRequestDto);
        appUser.setRole(Role.ROLE_USER);
       RegistrationResponseDto responseDto= userService.saveUser(appUser);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

}
