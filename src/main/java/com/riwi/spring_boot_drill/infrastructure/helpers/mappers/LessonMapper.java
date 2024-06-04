package com.riwi.spring_boot_drill.infrastructure.helpers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.riwi.spring_boot_drill.api.dtos.request.LessonRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.LessonUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.LessonResponse;
import com.riwi.spring_boot_drill.domain.entities.Lesson;
import com.riwi.spring_boot_drill.domain.repositories.CourseRepository;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.ICourseMapper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.ILessonMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LessonMapper implements ILessonMapper{

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final ICourseMapper courseMapper;

    @Override
    public Lesson requestToEntity(LessonRequest request) {
        return Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .courseId(serviceHelper.find(request.getCourseId(), courseRepository, "course"))
                .build();
    }

    @Override
    public LessonResponse entityToResponse(Lesson entity) {
        return LessonResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .courseId(this.courseMapper.entityToResponse(entity.getCourseId()))
                .build();
    }

    @Override
    public Lesson requestUpdateToEntity(LessonUpdateRequest request) {
        return Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }
    
}
