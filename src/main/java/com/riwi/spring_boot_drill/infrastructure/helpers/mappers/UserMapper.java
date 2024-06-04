package com.riwi.spring_boot_drill.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;

import com.riwi.spring_boot_drill.api.dtos.request.UserRequest;
import com.riwi.spring_boot_drill.api.dtos.response.UserResponse;
import com.riwi.spring_boot_drill.domain.entities.UserEntity;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IUserMapper;
import com.riwi.spring_boot_drill.utils.enums.RoleUser;

@Component
public class UserMapper implements IUserMapper{

    @Override
    public UserEntity requestToEntity(UserRequest request) {
        return UserEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .role(RoleUser.valueOf(request.getRole()))
                .build();
    }

    @Override
    public UserResponse entityToResponse(UserEntity entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .role(entity.getRole())
                .email(entity.getEmail())
                .build();
    }
    
}
