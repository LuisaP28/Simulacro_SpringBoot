package com.riwi.spring_boot_drill.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.spring_boot_drill.api.dtos.request.CourseRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.CourseUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.CourseReponse;
import com.riwi.spring_boot_drill.domain.entities.Course;
import com.riwi.spring_boot_drill.domain.repositories.CourseRepository;
import com.riwi.spring_boot_drill.domain.repositories.UserRepository;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.ICourseService;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.ICourseMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService implements
        ICourseService {

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final ICourseMapper courseMapper;

    @Override
    public CourseReponse create(CourseRequest request) {
        Course course = this.courseMapper.requestToEntity(request);
        return this.courseMapper.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public CourseReponse get(Long id) {
        Course course = this.serviceHelper.find(id, courseRepository, "course");
        return this.courseMapper.entityToResponse(course);
    }

    @Override
    public Page<CourseReponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;

        return this.courseRepository.findAll(PageRequest.of(page, size))
                .map((course)->this.courseMapper.entityToResponse(course));
    }

    @Override
    public CourseReponse update(Long id, CourseRequest request) {
        Course courseData = this.serviceHelper.find(id, courseRepository, "course");
        Course course = this.courseMapper.requestToEntity(request);

        if (request.getInstructorId() == null) {
            course.setInstructorId(courseData.getInstructorId());
        } else {
            this.serviceHelper.find(request.getInstructorId(), userRepository, "Instructor ID");
        }

        course.setId(id);
        return this.courseMapper.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public void delete(Long id) {
        Course course = this.serviceHelper.find(id, courseRepository, "course");
        this.courseRepository.delete(course);
    }

    @Override
    public CourseReponse updateInfo(Long id, CourseUpdateRequest request) {
        Course courseData = this.serviceHelper.find(id, courseRepository, "course");
        Course course = this.courseMapper.requestUpdateToEntity(request);
        course.setId(id);
        course.setInstructorId(courseData.getInstructorId());
        
        return this.courseMapper.entityToResponse(this.courseRepository.save(course));
    }
}
