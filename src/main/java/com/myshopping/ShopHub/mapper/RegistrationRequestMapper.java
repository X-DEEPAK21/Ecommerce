/*package com.myshopping.ShopHub.mapper;

import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.RequestDtos.RegistrationRequestDto;
import com.myshopping.ShopHub.ResponseDto.RegistrationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface RegistrationRequestMapper {

    AppUsers toEntity(RegistrationRequestDto registrationRequestDto);

    @Mapping(source = "appUser.phone_number",target = "contact_number")
    RegistrationResponseDto toDTO(AppUsers appUser);

}*/
