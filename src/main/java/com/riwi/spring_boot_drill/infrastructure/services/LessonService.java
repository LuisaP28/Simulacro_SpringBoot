package com.riwi.spring_boot_drill.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.spring_boot_drill.api.dtos.request.LessonRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.LessonUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.LessonResponse;
import com.riwi.spring_boot_drill.domain.entities.Lesson;
import com.riwi.spring_boot_drill.domain.repositories.LessonRepository;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.ILessonService;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.ILessonMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService{
    
    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final ILessonMapper lessonMapper;

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = this.lessonMapper.requestToEntity(request);
        return this.lessonMapper.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse get(Long id) {
        Lesson lesson = this.serviceHelper.find(id, lessonRepository, "lesson");
        return this.lessonMapper.entityToResponse(lesson);
    }

    @Override
    public Page<LessonResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;

        return this.lessonRepository.findAll(PageRequest.of(page, size))
                .map((lesson) -> this.lessonMapper.entityToResponse(lesson));
    }

    @Override
    public LessonResponse update(Long id, LessonRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        Lesson lesson = this.serviceHelper.find(id, lessonRepository, "lesson");
        this.lessonRepository.delete(lesson);
    }

    @Override
    public LessonResponse updateInfo(Long id, LessonUpdateRequest request) {
        Lesson lessonData = this.serviceHelper.find(id, lessonRepository, "lesson");
              
        Lesson lesson = this.lessonMapper.requestUpdateToEntity(request);
        lesson.setId(id);
        lesson.setCourseId(lessonData.getCourseId());
        
        return this.lessonMapper.entityToResponse(this.lessonRepository.save(lesson));
    }
}
