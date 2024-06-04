package com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers;

import com.riwi.spring_boot_drill.api.dtos.request.CourseRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.CourseUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.CourseReponse;
import com.riwi.spring_boot_drill.domain.entities.Course;

public interface ICourseMapper
        extends MapperBase<CourseRequest, Course, CourseReponse> {

        Course requestUpdateToEntity(CourseUpdateRequest request);
}
