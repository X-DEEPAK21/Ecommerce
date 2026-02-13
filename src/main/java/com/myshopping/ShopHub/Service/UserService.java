package com.myshopping.ShopHub.Service;

import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Repository.AppUserRepo;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.mapper.RegistrationRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private RegistrationRequestMapper registrationRequestMapper;

    @Autowired
    private AppUserRepo appUserRepo;

    public RegistrationResponseDto saveUser(AppUsers appUser){
       AppUsers appUser1= appUserRepo.save(appUser);
      return registrationRequestMapper.toDTO(appUser1);
    }


}
