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
import com.riwi.spring_boot_drill.api.dtos.request.UserRequest;
import com.riwi.spring_boot_drill.api.dtos.response.UserResponse;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
@Tag(name = "Users")
public class UserController
        implements ControllerBase<UserRequest, UserResponse, Long> {

    @Autowired
    private final IUserService userService;


    @PostMapping
    @Operation(
        summary = "Register a user",
        description = "To create a user, you must send: “username”, “password”, “email”, “role” and optionally “fullName”."
    )
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<UserResponse> create(
           @Validated @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.userService.create(request));
    }


    @GetMapping(path = "/{userId}")
    @Operation(
        summary = "Find a user by id",
        description = "To search for a user, the id must be passed and it must be of type Long."
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<UserResponse> get(
           @PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.get(userId));
    }


    @GetMapping
    @Operation(
        summary = "Search all users",
        description = "It will bring all saved users but paginated, by default it will be page: 1 and size:10, but you can modify it."
    )
    public ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.userService.getAll(page-1, size));
    }


    @PutMapping(path = "/{userId}")
    @Operation(
        summary = "Update a user",
        description = "To update for a user, the id must be passed and it must be of type Long and you must send: “username”, “password”, “email”, “role” and optionally “fullName”."
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorsResponse.class)))
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<UserResponse> update(
            @Validated @RequestBody UserRequest request,
            @PathVariable Long userId) {
        return ResponseEntity.ok(this.userService.update(userId, request));
    }


    @DeleteMapping(path = "/{userId}")
    @Operation(
        summary = "Delete a user",
        description = "To delete for a user, the id must be passed and it must be of type Long."
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorsResponse.class)))
    public ResponseEntity<Void> delete(
            @PathVariable Long userId) {
        this.userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
