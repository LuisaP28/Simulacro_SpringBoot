package com.riwi.spring_boot_drill.infrastructure.helpers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.riwi.spring_boot_drill.api.dtos.request.CourseRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.CourseUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.CourseReponse;
import com.riwi.spring_boot_drill.domain.entities.Course;
import com.riwi.spring_boot_drill.domain.entities.UserEntity;
import com.riwi.spring_boot_drill.domain.repositories.UserRepository;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.ICourseMapper;
import com.riwi.spring_boot_drill.utils.enums.RoleUser;
import com.riwi.spring_boot_drill.utils.exceptions.NotAuthorizedException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CourseMapper implements ICourseMapper {

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public Course requestToEntity(CourseRequest request) {
        
        UserEntity instructor = this.serviceHelper.find(request.getInstructorId(), userRepository, "instructor");

        if (instructor.getRole() == RoleUser.STUDENT) {
            throw new NotAuthorizedException("A student cannot be a course instructor");
        }

        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .instructorId(instructor)
                .build();
    }

    @Override
    public CourseReponse entityToResponse(Course entity) {
        return CourseReponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .instructorId(this.userMapper.entityToResponse(entity.getInstructorId()))
                .build();
    }

    @Override
    public Course requestUpdateToEntity(CourseUpdateRequest request) {
        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

}
