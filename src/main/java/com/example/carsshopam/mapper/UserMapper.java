package com.example.carsshopam.mapper;

import com.example.carsshopam.dto.CreateUserDto;
import com.example.carsshopam.dto.UserDto;
import com.example.carsshopam.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(CreateUserDto createUserDto);

    UserDto map(User user);


}
