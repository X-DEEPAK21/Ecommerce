package com.myshopping.ShopHub.Controller;


import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Entity.Role;
import com.myshopping.ShopHub.RequestDtos.LoginRequestDto;
import com.myshopping.ShopHub.RequestDtos.RegistrationRequestDto;
import com.myshopping.ShopHub.RequestDtos.UpdateRequestDto;
import com.myshopping.ShopHub.ResponseDto.LoginResponseDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.ResponseDto.UpdateUserDto;
import com.myshopping.ShopHub.Security.Token;
import com.myshopping.ShopHub.Security.UserDetailImpl;
import com.myshopping.ShopHub.Service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/user")
public class UserController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    Token token_class;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> saveUser(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){

        AppUsers appUser=modelMapper.map(registrationRequestDto,AppUsers.class);
        appUser.setRole(Role.ROLE_USER);
       RegistrationResponseDto responseDto= userService.saveUser(appUser);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword());
       Authentication authentication= authenticationManager.authenticate(token);
      AppUsers users=((UserDetailImpl)authentication.getPrincipal()).getAppUsers();
      String jwt_access_Token=token_class.generateAccessToken(users);

      LoginResponseDto loginResponseDto= modelMapper.map(users, LoginResponseDto.class);
      loginResponseDto.setToken(jwt_access_Token);

      return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseDto);

    }
    //test

    @PatchMapping("/update/{emailId}")
    public ResponseEntity<UpdateUserDto> updateUser(@PathVariable("emailId")String email, @RequestBody UpdateRequestDto updateRequestDto){
      UpdateUserDto updateUserDto= userService.updateUser(email,updateRequestDto);
      return ResponseEntity.status(HttpStatus.OK).body(updateUserDto);
    }




}
