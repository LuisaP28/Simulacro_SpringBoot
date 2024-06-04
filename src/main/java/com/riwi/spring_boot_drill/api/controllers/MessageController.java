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
import com.riwi.spring_boot_drill.api.dtos.request.MessageRequest;
import com.riwi.spring_boot_drill.api.dtos.response.MessageResponse;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IMessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/messages")
@AllArgsConstructor
@Tag(name = "Messages")
public class MessageController implements
        ControllerBase<MessageRequest, MessageResponse, Long> {

       @Autowired
    private final IMessageService messageService;

    @PostMapping
    @Operation(
        summary = "Send a message",
        description = "To send a message, you must send: “senderId”, “receiverId”, “courseId”, and “content”."
    )
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<MessageResponse> create(@Validated @RequestBody MessageRequest request) {
        return ResponseEntity.ok(this.messageService.create(request));
    }

    @GetMapping(path = "/{messageId}")
    public ResponseEntity<MessageResponse> get(@PathVariable Long messageId) {
        return ResponseEntity.ok(this.messageService.get(messageId));
    }

    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getAll(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.messageService.getAll(page-1, size));
    }

    @DeleteMapping(path = "/{messageId}")
    public ResponseEntity<Void> delete(@PathVariable Long messageId) {
        this.messageService.delete(messageId);
        return ResponseEntity.noContent().build();
    }
}
