package com.riwi.spring_boot_drill.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.spring_boot_drill.api.dtos.request.UserRequest;
import com.riwi.spring_boot_drill.api.dtos.response.UserResponse;
import com.riwi.spring_boot_drill.domain.entities.UserEntity;
import com.riwi.spring_boot_drill.domain.repositories.UserRepository;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IUserService;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IUserMapper;
import com.riwi.spring_boot_drill.utils.enums.RoleUser;
import com.riwi.spring_boot_drill.utils.exceptions.BadRoleException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final IUserMapper userMapper;

    @Override
    public UserResponse create(UserRequest request) {

        if (request.getRole() == null) {
            throw new BadRoleException("Role is required");
        }

        try {
            RoleUser.valueOf(request.getRole());
        } catch (Exception e) {
            throw new BadRoleException("Role is invalid");
        }

        UserEntity user = this.userMapper.requestToEntity(request);
        return this.userMapper.entityToResponse(this.userRepository.save(user));
    }

    @Override
    public UserResponse get(Long id) {
        UserEntity user = this.serviceHelper.find(id, userRepository, "user");
        return this.userMapper.entityToResponse(user);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;

        return this.userRepository.findAll(PageRequest.of(page, size))
                .map((entity) -> this.userMapper.entityToResponse(entity));
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {

        UserEntity userData = this.serviceHelper.find(id, userRepository, "user");

        if (userData.getRole() == RoleUser.ADMIN) {
            try {
                if (request.getRole() != null) {
                    RoleUser.valueOf(request.getRole());
                }
            } catch (Exception e) {
                throw new BadRoleException("Role is invalid");
            }
        } else {
            if (request.getRole() != null) {
                throw new BadRoleException("Changing the role is not allowed");
            }
        }

        if (request.getRole() == null) {
            request.setRole(userData.getRole().name());
        }

        UserEntity user = this.userMapper.requestToEntity(request);
        user.setId(id);

        return this.userMapper.entityToResponse(this.userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        UserEntity user = this.serviceHelper.find(id, userRepository, "user");
        this.userRepository.delete(user);
    }

}
