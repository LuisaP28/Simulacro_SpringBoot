package com.riwi.spring_boot_drill.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.spring_boot_drill.api.dtos.errors.ErrorsResponse;
import com.riwi.spring_boot_drill.api.dtos.request.EnrollmentRequest;
import com.riwi.spring_boot_drill.api.dtos.response.EnrollmentResponse;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IEnrollmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/enrollments")
@AllArgsConstructor
@Tag(name = "Enrollments")
public class EnrollmentController 
        implements ControllerBase<EnrollmentRequest, EnrollmentResponse, Long>    {
    
     @Autowired
    private final IEnrollmentService enrollmentService;

    @PostMapping
    @Operation(
        summary = "Register an enrollment",
        description = "To create an enrollment, you must send: “userId”, “courseId”, and “date”."
    )
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<EnrollmentResponse> create(@Validated @RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(this.enrollmentService.create(request));
    }

    @GetMapping(path = "/{enrollmentId}")
    public ResponseEntity<EnrollmentResponse> get(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(this.enrollmentService.get(enrollmentId));
    }

    @GetMapping
    public ResponseEntity<Page<EnrollmentResponse>> getAll(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.enrollmentService.getAll(page-1, size));
    }

    @DeleteMapping(path = "/{enrollmentId}")
    public ResponseEntity<Void> delete(@PathVariable Long enrollmentId) {
        this.enrollmentService.delete(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}
