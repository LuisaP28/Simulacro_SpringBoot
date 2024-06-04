package com.riwi.spring_boot_drill.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.spring_boot_drill.api.dtos.request.EnrollmentRequest;
import com.riwi.spring_boot_drill.api.dtos.response.EnrollmentResponse;

public interface IEnrollmentService extends ServiceBase<EnrollmentRequest, EnrollmentResponse, Long> {
EnrollmentResponse create(EnrollmentRequest request);
    EnrollmentResponse get(Long id);
    Page<EnrollmentResponse> getAll(int page, int size);
    void delete(Long id);
}
