package com.capstone.sporting_event.user_service.dto;

import com.capstone.sporting_event.user_service.constants.Role;
import com.capstone.sporting_event.user_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserRequestDTO toDTO(User entity){

        return UserRequestDTO.builder()
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    public UserDisplayDTO toDisplayDTO(User entity){

        return UserDisplayDTO.builder()
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .role(entity.getRole().toString())
                .build();
    }

    public User toEntity(UserRequestDTO dto){

        return User.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public User toEntityForUser(UserRequestDTO dto){

        return User.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();
    }

    public User toEntityForAdmin(UserRequestDTO dto){

        return User.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode( dto.getPassword()))
                .role(Role.ADMIN)
                .build();
    }






}
