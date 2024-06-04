package com.riwi.spring_boot_drill.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.spring_boot_drill.api.dtos.request.AssignmentRequest;
import com.riwi.spring_boot_drill.api.dtos.response.AssignmentResponse;

public interface IAssignmentService extends ServiceBase<AssignmentRequest, AssignmentResponse, Long> {
        AssignmentResponse create(AssignmentRequest request);
        AssignmentResponse get(Long id);
        Page<AssignmentResponse> getAll(int page, int size);
        AssignmentResponse update(Long id, AssignmentRequest request);
        void delete(Long id);
}
