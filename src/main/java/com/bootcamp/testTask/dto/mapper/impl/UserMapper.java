package com.bootcamp.testTask.dto.mapper.impl;

import com.bootcamp.testTask.dto.UserDto;
import com.bootcamp.testTask.dto.mapper.Mapper;
import com.bootcamp.testTask.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper implements Mapper<UserDto, Users> {

    private final PasswordEncoder passwordEncoder;
    private final ItemMapper itemMapper;
    @Override
    public UserDto toDto(Users user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    @Override
    public Users toEntity(UserDto userDto) {
        return Users.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }

    public UserDto toDtoWithItems(Users user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .itemsToSellDto(itemMapper.listToDto(user.getItemsToSell()))
                .itemsToBuyDto(itemMapper.listToDto(user.getItemsToBuy()))
                .build();
    }

}
