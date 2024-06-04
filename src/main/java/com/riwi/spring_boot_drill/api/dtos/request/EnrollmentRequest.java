package com.riwi.spring_boot_drill.api.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequest {
    @NotNull(message = "User is required")
    private Long userId;

    @NotNull(message = "Course is required")
    private Long courseId;
}
