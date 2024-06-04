package com.riwi.spring_boot_drill.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseReponse {
    private Long id;
    private String name;
    private String description;
    private UserResponse instructorId;
}
