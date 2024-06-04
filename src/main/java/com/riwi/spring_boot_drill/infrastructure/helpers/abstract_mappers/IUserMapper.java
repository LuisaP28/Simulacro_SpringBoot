package com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers;

import com.riwi.spring_boot_drill.api.dtos.request.UserRequest;
import com.riwi.spring_boot_drill.api.dtos.response.UserResponse;
import com.riwi.spring_boot_drill.domain.entities.UserEntity;

public interface IUserMapper
        extends MapperBase<UserRequest, UserEntity, UserResponse> {

}
