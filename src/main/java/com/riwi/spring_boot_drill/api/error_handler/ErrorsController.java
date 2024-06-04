package com.riwi.spring_boot_drill.api.error_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.riwi.spring_boot_drill.api.dtos.errors.ErrorSimpleResponse;
import com.riwi.spring_boot_drill.api.dtos.errors.ErrorsResponse;
import com.riwi.spring_boot_drill.utils.exceptions.BadIdException;
import com.riwi.spring_boot_drill.utils.exceptions.BadRoleException;
import com.riwi.spring_boot_drill.utils.exceptions.NotAuthorizedException;

@RestControllerAdvice
public class ErrorsController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorsResponse handlerBadRequest(MethodArgumentNotValidException exception) {

        List<Map<String, String>> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            Map<String, String> errorResponse = new HashMap<>();

            errorResponse.put("Error", error.getDefaultMessage());
            errorResponse.put("Field", error.getField());

            errors.add(errorResponse);
        });
        
        return ErrorsResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(BadIdException.class)
    public ErrorsResponse handlerIdError(BadIdException exception) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("Error", "ID not found");
        errorResponse.put("Entity", exception.getMessage());
        errors.add(errorResponse);

        return ErrorsResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(BadRoleException.class)
    public ErrorsResponse handlerRoleError(BadRoleException exception) {
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("Error", exception.getMessage());
        errors.add(errorResponse);

        return ErrorsResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ErrorSimpleResponse handlerAuthorizedError(NotAuthorizedException exception) {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("Error", exception.getMessage());

        return ErrorSimpleResponse.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .status(HttpStatus.FORBIDDEN.name())
                .error(errorResponse)
                .build();
    }
}
