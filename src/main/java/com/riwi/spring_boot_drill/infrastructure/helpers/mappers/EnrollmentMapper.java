package com.riwi.spring_boot_drill.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;

import com.riwi.spring_boot_drill.api.dtos.request.EnrollmentRequest;
import com.riwi.spring_boot_drill.api.dtos.response.EnrollmentResponse;
import com.riwi.spring_boot_drill.domain.entities.Enrollment;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IEnrollmentMapper;

@Component
public class EnrollmentMapper implements IEnrollmentMapper{

    @Override
    public Enrollment requestToEntity(EnrollmentRequest request) {
        return Enrollment.builder()
                .courseId(null)
                .userId(null)
                .build();
    }

    @Override
    public EnrollmentResponse entityToResponse(Enrollment entity) {
        return EnrollmentResponse.builder()
                .id(entity.getId())
                .userId(null)
                .courseId(null)
                .date(entity.getDate())
                .build();
    }
    
}
