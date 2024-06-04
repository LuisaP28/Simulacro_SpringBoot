package com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers;

import com.riwi.spring_boot_drill.api.dtos.request.AssignmentRequest;
import com.riwi.spring_boot_drill.api.dtos.response.AssignmentResponse;
import com.riwi.spring_boot_drill.domain.entities.Assignment;

public interface IAssignmentMapper
        extends MapperBase<AssignmentRequest, Assignment, AssignmentResponse> {

}
