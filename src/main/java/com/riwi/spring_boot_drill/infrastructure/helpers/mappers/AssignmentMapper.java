package com.riwi.spring_boot_drill.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;

import com.riwi.spring_boot_drill.api.dtos.request.AssignmentRequest;
import com.riwi.spring_boot_drill.api.dtos.response.AssignmentResponse;
import com.riwi.spring_boot_drill.domain.entities.Assignment;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IAssignmentMapper;

@Component
public class AssignmentMapper implements IAssignmentMapper{

    @Override
    public Assignment requestToEntity(AssignmentRequest request) {
        return Assignment.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .lessonId(null)
                .build();
    }

    @Override
    public AssignmentResponse entityToResponse(Assignment entity) {
        return AssignmentResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .date(entity.getDate())
                .lessonId(null)
                .build();
    }
    
}
