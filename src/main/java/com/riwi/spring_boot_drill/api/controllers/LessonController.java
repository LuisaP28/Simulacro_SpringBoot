package com.riwi.spring_boot_drill.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.spring_boot_drill.api.dtos.request.LessonRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.LessonUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.LessonResponse;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.ILessonService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
@Tag(name = "Lessons")
public class LessonController 
        implements ControllerBase<LessonRequest, LessonResponse, Long> {

    @Autowired
    private final ILessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> create(
        @Validated @RequestBody LessonRequest request) {
        return ResponseEntity.ok(this.lessonService.create(request));
    }

    @GetMapping(path = "/{lessonId}")
    public ResponseEntity<LessonResponse> get(@PathVariable Long lessonId) {
        return ResponseEntity.ok(this.lessonService.get(lessonId));
    }

    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAll(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.lessonService.getAll(page-1, size));
    }

    @PutMapping(path = "/{lessonId}")
    public ResponseEntity<LessonResponse> update(
        @Validated @RequestBody LessonRequest request,
        @PathVariable Long lessonId) {
        return ResponseEntity.ok(this.lessonService.update(lessonId, request));
    }

    @PutMapping(path = "/info/{lessonId}")
    public ResponseEntity<LessonResponse> updateInfo(
        @Validated @RequestBody LessonUpdateRequest request, 
        @PathVariable Long lessonId) {
        return ResponseEntity.ok(this.lessonService.updateInfo(lessonId, request));
    }

    @DeleteMapping(path = "/{lessonId}")
    public ResponseEntity<Void> delete(Long lessonId) {
        this.lessonService.delete(lessonId);
        return ResponseEntity.noContent().build();
    }
}
