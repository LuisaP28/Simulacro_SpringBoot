package com.riwi.spring_boot_drill.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.spring_boot_drill.api.dtos.request.SubmissionRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.SubmissionUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.SubmissionResponse;

public interface ISubmissionService
        extends ServiceBase<SubmissionRequest, SubmissionResponse, Long> {

        SubmissionResponse create(SubmissionRequest request);
        SubmissionResponse get(Long id);
        Page<SubmissionResponse> getAll(int page, int size);
        SubmissionResponse update(Long id, SubmissionRequest request);
        void delete(Long id);
        SubmissionResponse updateInfo(Long id, SubmissionUpdateRequest request);
}
