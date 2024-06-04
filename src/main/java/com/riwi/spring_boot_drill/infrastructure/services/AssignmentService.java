package com.riwi.spring_boot_drill.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.spring_boot_drill.api.dtos.request.AssignmentRequest;
import com.riwi.spring_boot_drill.api.dtos.request.update.AssignmentUpdateRequest;
import com.riwi.spring_boot_drill.api.dtos.response.AssignmentResponse;
import com.riwi.spring_boot_drill.domain.entities.Assignment;
import com.riwi.spring_boot_drill.domain.repositories.AssignmentRepository;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IAssignmentService;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IAssignmentMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService{
    
    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final IAssignmentMapper assignmentMapper;

    @Override
    public AssignmentResponse create(AssignmentRequest request) {
        Assignment assignment = this.assignmentMapper.requestToEntity(request);
        return this.assignmentMapper.entityToResponse(this.assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentResponse get(Long id) {
        Assignment assignment = this.serviceHelper.find(id, assignmentRepository, "assignment");
        return this.assignmentMapper.entityToResponse(assignment);
    }

    @Override
    public Page<AssignmentResponse> getAll(int page, int size) {
       if (page < 0) page = 0;
        if (size < 1) size = 10;

        return this.assignmentRepository.findAll(PageRequest.of(page, size))
                .map(this.assignmentMapper::entityToResponse);
    }

    @Override
    public AssignmentResponse update(Long id, AssignmentRequest request) {
        Assignment assignmentData = this.serviceHelper.find(id, assignmentRepository, "assignment");
        Assignment assignment = this.assignmentMapper.requestToEntity(request);

        assignment.setId(id);
        return this.assignmentMapper.entityToResponse(this.assignmentRepository.save(assignment));
    }

    @Override
    public void delete(Long id) {
        Assignment assignment = this.serviceHelper.find(id, assignmentRepository, "assignment");
        this.assignmentRepository.delete(assignment);
    }

    @Override
    public AssignmentResponse updateInfo(Long id, AssignmentUpdateRequest request) {
        Assignment assignmentData = this.serviceHelper.find(id, assignmentRepository, "assignment");
        Assignment assignment = this.assignmentMapper.requestUpdateToEntity(request);
        assignment.setId(id);

        return this.assignmentMapper.entityToResponse(this.assignmentRepository.save(assignment));
    }
}
