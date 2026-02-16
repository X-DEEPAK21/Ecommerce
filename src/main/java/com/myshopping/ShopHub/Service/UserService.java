package com.myshopping.ShopHub.Service;

import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Exception.UserAlreadyExist;
import com.myshopping.ShopHub.Exception.UserNotFoundException;
import com.myshopping.ShopHub.Repository.AppUserRepo;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.mapper.RegistrationRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private RegistrationRequestMapper registrationRequestMapper;
    @Autowired
     PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepo appUserRepo;

    @Transactional
    public RegistrationResponseDto saveUser(AppUsers appUser){
        String password=passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(password);
       AppUsers appUser1= appUserRepo.save(appUser);
      return registrationRequestMapper.toDTO(appUser1);
    }

    public void checkUserExist(String  email){
      Optional<AppUsers> appUsers= appUserRepo.findByEmail(email);
      if(appUsers.isPresent()) throw new UserAlreadyExist("User Already exist ");
    }
    public AppUsers getUser(String email){
       Optional<AppUsers> user= appUserRepo.findByEmail(email);
       return user.get();
    }

    public AppUsers findUserByEmail(String email){
     Optional<AppUsers> user =appUserRepo.findByEmail(email);
     if(user.isEmpty())throw new UserNotFoundException("User not found ");
     return user.get();
    }


}
