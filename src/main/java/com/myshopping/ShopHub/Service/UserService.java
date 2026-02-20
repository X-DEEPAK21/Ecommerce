package com.myshopping.ShopHub.Service;

import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Exception.UserAlreadyExist;
import com.myshopping.ShopHub.Exception.UserNotFoundException;
import com.myshopping.ShopHub.Repository.AppUserRepo;
import com.myshopping.ShopHub.RequestDtos.UpdateRequestDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;

import com.myshopping.ShopHub.ResponseDto.UpdateUserDto;
import com.myshopping.ShopHub.Security.UserDetailImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
     PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepo appUserRepo;
    private final String CACHE_NAME="app_user";

    @Transactional
    public RegistrationResponseDto saveUser(AppUsers appUser){
        String password=passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(password);
        appUser.setCreated_by(appUser.getEmail());
       AppUsers appUser1= appUserRepo.save(appUser);
      RegistrationResponseDto dto= modelMapper.map(appUser1, RegistrationResponseDto.class);
      dto.setContact_number(appUser1.getPhone_number());
      return dto;
    }

    public void checkUserExist(String  email){
      Optional<AppUsers> appUsers= appUserRepo.findByEmail(email);
      if(appUsers.isPresent()) throw new UserAlreadyExist("User Already exist ");
    }
    public AppUsers getUser(String email){
       Optional<AppUsers> user= appUserRepo.findByEmail(email);
       return user.get();
    }

    @Cacheable(cacheNames = CACHE_NAME,key = "#email")
    public AppUsers findUserByEmail(String email){
     Optional<AppUsers> user =appUserRepo.findByEmail(email);
     if(user.isEmpty())throw new UserNotFoundException("User not found ");
     return user.get();
    }

    @CachePut
    @Transactional
    public UpdateUserDto updateUser(String email, UpdateRequestDto updateRequestDto) {
        AppUsers user1 = this.findUserByEmail(email);
        modelMapper.map(updateRequestDto, user1);
        user1.setPhone_number(updateRequestDto.getContact_number());
        UserDetailImpl userDetailImpl = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUsers setUser = userDetailImpl.getAppUsers();
        user1.setUpdated_by(setUser.getEmail());
        AppUsers updateUser = appUserRepo.save(user1);
        UpdateUserDto updateUserDto = new UpdateUserDto();
        modelMapper.map(updateUser, updateUserDto);
        updateUserDto.setContact_number(updateUser.getPhone_number());
    return updateUserDto;


    }


}
