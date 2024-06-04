package com.riwi.spring_boot_drill.infrastructure.abstract_services;

import com.riwi.spring_boot_drill.api.dtos.request.UserRequest;
import com.riwi.spring_boot_drill.api.dtos.response.UserResponse;

public interface IUserService
        extends ServiceBase<UserRequest, UserResponse, Long> {

}
