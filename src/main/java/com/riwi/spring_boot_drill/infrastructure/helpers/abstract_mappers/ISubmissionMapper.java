package com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers;

import com.riwi.spring_boot_drill.api.dtos.request.SubmissionRequest;
import com.riwi.spring_boot_drill.api.dtos.response.SubmissionResponse;
import com.riwi.spring_boot_drill.domain.entities.Submission;

public interface ISubmissionMapper
        extends MapperBase<SubmissionRequest, Submission, SubmissionResponse> {

}
