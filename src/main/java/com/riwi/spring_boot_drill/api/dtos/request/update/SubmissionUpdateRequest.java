package com.riwi.spring_boot_drill.api.dtos.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmissionUpdateRequest {
    @NotBlank(message = "Content is required")
    private String content;
}
