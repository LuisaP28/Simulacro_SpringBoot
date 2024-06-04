package com.riwi.spring_boot_drill.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.spring_boot_drill.api.dtos.request.SubmissionRequest;
import com.riwi.spring_boot_drill.api.dtos.response.SubmissionResponse;
import com.riwi.spring_boot_drill.domain.entities.Submission;
import com.riwi.spring_boot_drill.domain.repositories.SubmissionRepository;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.ISubmissionService;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.ISubmissionMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionService implements ISubmissionService{
    
    @Autowired
    private final SubmissionRepository submissionRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final ISubmissionMapper submissionMapper;

    @Override
    public SubmissionResponse create(SubmissionRequest request) {
        Submission submission = this.submissionMapper.requestToEntity(request);
        return this.submissionMapper.entityToResponse(this.submissionRepository.save(submission));
    }

    @Override
    public SubmissionResponse get(Long id) {
        Submission submission = this.serviceHelper.find(id, submissionRepository, "submission");
        return this.submissionMapper.entityToResponse(submission);
    }

    @Override
    public Page<SubmissionResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;

        return this.submissionRepository.findAll(PageRequest.of(page, size))
                .map(this.submissionMapper::entityToResponse);
    }

    @Override
    public SubmissionResponse update(Long id, SubmissionRequest request) {
        Submission submissionData = this.serviceHelper.find(id, submissionRepository, "submission");
        Submission submission = this.submissionMapper.requestToEntity(request);

        submission.setId(id);
        return this.submissionMapper.entityToResponse(this.submissionRepository.save(submission));
    }

    @Override
    public void delete(Long id) {
        Submission submission = this.serviceHelper.find(id, submissionRepository, "submission");
        this.submissionRepository.delete(submission);
    }

    @Override
    public SubmissionResponse updateInfo(Long id,   SubmissionUpdateRequest request) {
        Submission submissionData = this.serviceHelper.find(id, submissionRepository, "submission");
        Submission submission = this.submissionMapper.requestUpdateToEntity(request);
        submission.setId(id);

        return this.submissionMapper.entityToResponse(this.submissionRepository.save(submission));
    }
}
