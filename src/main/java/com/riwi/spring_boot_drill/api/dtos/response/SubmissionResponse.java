package com.riwi.spring_boot_drill.api.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponse {
    private Long id;
    private String content;
    private LocalDateTime date;
    private BigDecimal grade;
    private UserResponse userId;
    private AssignmentResponse assignmentId;
}
