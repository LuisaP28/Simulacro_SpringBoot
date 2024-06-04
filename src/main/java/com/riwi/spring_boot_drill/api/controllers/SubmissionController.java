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
import com.riwi.spring_boot_drill.api.dtos.request.SubmissionRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.SubmissionUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.SubmissionResponse;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.ISubmissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/submissions")
@AllArgsConstructor
@Tag(name = "Submissions")
public class SubmissionController
        implements ControllerBase<SubmissionRequest, SubmissionResponse, Long> {

    @Autowired
    private final ISubmissionService submissionService;

    @PostMapping
    @Operation(
        summary = "Create a submission",
        description = "To create a submission, you must send: “content”, “date”, “userId”, and “assignmentId”."
    )
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<SubmissionResponse> create(@Validated @RequestBody SubmissionRequest request) {
        return ResponseEntity.ok(this.submissionService.create(request));
    }

    @GetMapping(path = "/{submissionId}")
    public ResponseEntity<SubmissionResponse> get(@PathVariable Long submissionId) {
        return ResponseEntity.ok(this.submissionService.get(submissionId));
    }

    @GetMapping
    public ResponseEntity<Page<SubmissionResponse>> getAll(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.submissionService.getAll(page-1, size));
    }

    @PutMapping(path = "/{submissionId}")
    public ResponseEntity<SubmissionResponse> update(
        @Validated @RequestBody SubmissionRequest request, 
        @PathVariable Long submissionId) {
        return ResponseEntity.ok(this.submissionService.update(submissionId, request));
    }

    @PutMapping(path = "/info/{submissionId}")
    public ResponseEntity<SubmissionResponse> updateInfo(
        @Validated @RequestBody SubmissionUpdateRequest request, 
        @PathVariable Long submissionId) {
        return ResponseEntity.ok(this.submissionService.updateInfo(submissionId, request));
    }

    @DeleteMapping(path = "/{submissionId}")
    public ResponseEntity<Void> delete(@PathVariable Long submissionId) {
        this.submissionService.delete(submissionId);
        return ResponseEntity.noContent().build();
    }
}
