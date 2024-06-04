package com.riwi.spring_boot_drill.infrastructure.abstract_services;

import com.riwi.spring_boot_drill.api.dtos.request.LessonRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.LessonUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.LessonResponse;

public interface ILessonService
        extends ServiceBase<LessonRequest, LessonResponse, Long> {

        public LessonResponse updateInfo(Long id,LessonUpdateRequest request);
}
