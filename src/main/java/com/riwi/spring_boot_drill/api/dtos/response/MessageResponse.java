package com.riwi.spring_boot_drill.api.dtos.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private Long id;
    private UserResponse senderId;
    private UserResponse receiverId;
    private CourseReponse courseId;
    private String content;
    private LocalDateTime date;
}
