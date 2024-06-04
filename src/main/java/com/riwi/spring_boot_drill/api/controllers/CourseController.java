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

import com.riwi.spring_boot_drill.api.dtos.errors.ErrorsResponse;
import com.riwi.spring_boot_drill.api.dtos.request.CourseRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.CourseUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.CourseReponse;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.ICourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
@Tag(name = "Courses")
public class CourseController
        implements ControllerBase<CourseRequest, CourseReponse, Long> {

    @Autowired
    private final ICourseService courseService;

    @PostMapping
    @Operation(
        summary = "Register a course",
        description = "To create a course, you must send: “name”, “instructorId”, and optionally “description”."
    )
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<CourseReponse> create(@Validated @RequestBody CourseRequest request) {
        return ResponseEntity.ok(this.courseService.create(request));
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<CourseReponse> get(@PathVariable Long courseId) {
        return ResponseEntity.ok(this.courseService.get(courseId));
    }

    @GetMapping
    public ResponseEntity<Page<CourseReponse>> getAll(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.courseService.getAll(page-1, size));
    }

    @PutMapping(path = "/{courseId}")
    public ResponseEntity<CourseReponse> update(
        @Validated @RequestBody CourseRequest request, 
        @PathVariable Long courseId) {
        return ResponseEntity.ok(this.courseService.update(courseId, request));
    }

    @PutMapping(path = "/info/{courseId}")
    public ResponseEntity<CourseReponse> updateInfo(
        @Validated @RequestBody CourseUpdateRequest request, 
        @PathVariable Long courseId) {
        return ResponseEntity.ok(this.courseService.updateInfo(courseId, request));
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long courseId) {
        this.courseService.delete(courseId);
        return ResponseEntity.noContent().build();
    }

}
