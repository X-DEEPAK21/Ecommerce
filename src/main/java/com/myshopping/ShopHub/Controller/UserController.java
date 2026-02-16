package com.myshopping.ShopHub.Controller;


import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Entity.Role;
import com.myshopping.ShopHub.RequestDtos.LoginRequestDto;
import com.myshopping.ShopHub.RequestDtos.RegistrationRequestDto;
import com.myshopping.ShopHub.ResponseDto.LoginResponseDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.Security.Token;
import com.myshopping.ShopHub.Security.UserDetailImpl;
import com.myshopping.ShopHub.Service.UserService;
import com.myshopping.ShopHub.mapper.LoginResponseMapper;
import com.myshopping.ShopHub.mapper.RegistrationRequestMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app/user")
public class UserController {
    @Autowired
    LoginResponseMapper loginResponseMapper;

    @Autowired
    RegistrationRequestMapper registrationRequestMapper;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    Token token_class;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> saveUser(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){

        AppUsers appUser=registrationRequestMapper.toEntity(registrationRequestDto);
        appUser.setRole(Role.ROLE_USER);
       RegistrationResponseDto responseDto= userService.saveUser(appUser);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword());
       Authentication authentication= authenticationManager.authenticate(token);
      AppUsers users=((UserDetailImpl)authentication.getPrincipal()).getAppUsers();
      String jwt_access_Token=token_class.generateAccessToken(users);

      LoginResponseDto loginResponseDto= loginResponseMapper.toDTO(users);
      loginResponseDto.setToken(jwt_access_Token);

      return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseDto);

    }

}
