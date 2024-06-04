package com.riwi.spring_boot_drill.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.riwi.spring_boot_drill.api.dtos.errors.ErrorsResponse;
import com.riwi.spring_boot_drill.api.dtos.request.EnrollmentRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.EnrollmentUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.EnrollmentResponse;
import com.riwi.spring_boot_drill.domain.entities.Enrollment;
import com.riwi.spring_boot_drill.domain.repositories.EnrollmentRepository;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IEnrollmentService;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IEnrollmentMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnrollmentService implements IEnrollmentService{
    
    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final IEnrollmentMapper enrollmentMapper;

   @PostMapping
    @Operation(
        summary = "Create an enrollment",
        description = "To create an enrollment, you must send: “userId”, “courseId”, and optionally “date”."
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

    @PutMapping(path = "/{enrollmentId}")
    public ResponseEntity<EnrollmentResponse> update(
        @Validated @RequestBody EnrollmentRequest request,
        @PathVariable Long enrollmentId) {
        return ResponseEntity.ok(this.EnrollmentService.update(enrollmentId, request));
    }

    @PutMapping(path = "/info/{enrollmentId}")
    public ResponseEntity<EnrollmentResponse> updateInfo(
        @Validated @RequestBody EnrollmentUpdateRequest request,
        @PathVariable Long enrollmentId) {
        return ResponseEntity.ok(this.enrollmentService.updateInfo(enrollmentId, request));
    }

    @DeleteMapping(path = "/{enrollmentId}")
    public ResponseEntity<Void> delete(@PathVariable Long enrollmentId) {
        this.EnrollmentService.delete(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}
