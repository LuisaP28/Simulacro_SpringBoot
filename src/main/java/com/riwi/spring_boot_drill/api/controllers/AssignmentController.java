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
import com.riwi.spring_boot_drill.api.dtos.request.AssignmentRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.AssignmentUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.AssignmentResponse;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IAssignmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/assignments")
@AllArgsConstructor
@Tag(name = "Assignments")
public class AssignmentController 
        implements ControllerBase<AssignmentRequest, AssignmentResponse , Long> {
    
    @Autowired
    private final IAssignmentService assignmentService;

    @PostMapping
    @Operation(
        summary = "Register an assignment",
        description = "To create an assignment, you must send: “title”, “description”, “date”, and “lessonId”."
    )
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<AssignmentResponse> create(@Validated @RequestBody AssignmentRequest request) {
        return ResponseEntity.ok(this.assignmentService.create(request));
    }

    @GetMapping(path = "/{assignmentId}")
    public ResponseEntity<AssignmentResponse> get(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(this.assignmentService.get(assignmentId));
    }

    @GetMapping
    public ResponseEntity<Page<AssignmentResponse>> getAll(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.assignmentService.getAll(page-1, size));
    }

    @PutMapping(path = "/{assignmentId}")
    public ResponseEntity<AssignmentResponse> update(
        @Validated @RequestBody AssignmentRequest request, 
        @PathVariable Long assignmentId) {
        return ResponseEntity.ok(this.assignmentService.update(assignmentId, request));
    }

    @PutMapping(path = "/info/{assignmentId}")
    public ResponseEntity<AssignmentResponse> updateInfo(
        @Validated @RequestBody AssignmentUpdateRequest request, 
        @PathVariable Long assignmentId) {
        return ResponseEntity.ok(this.assignmentService.updateInfo(assignmentId, request));
    }

    @DeleteMapping(path = "/{assignmentId}")
    public ResponseEntity<Void> delete(@PathVariable Long assignmentId) {
        this.assignmentService.delete(assignmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/lessons/{lessonId}/assignments")
    public ResponseEntity<Page<AssignmentResponse>> getAllByLesson(
        @PathVariable Long lessonId,
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.assignmentService.getAllByLesson(lessonId, page-1, size));
    }
}
