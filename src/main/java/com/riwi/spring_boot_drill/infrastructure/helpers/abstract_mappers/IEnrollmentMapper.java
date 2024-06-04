package com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers;

import com.riwi.spring_boot_drill.api.dtos.request.EnrollmentRequest;
import com.riwi.spring_boot_drill.api.dtos.response.EnrollmentResponse;
import com.riwi.spring_boot_drill.domain.entities.Enrollment;

public interface IEnrollmentMapper
        extends MapperBase<EnrollmentRequest, Enrollment, EnrollmentResponse> {

}
