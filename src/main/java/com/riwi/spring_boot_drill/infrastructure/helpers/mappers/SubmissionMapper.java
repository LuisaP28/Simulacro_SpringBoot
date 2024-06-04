package com.riwi.spring_boot_drill.infrastructure.helpers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.riwi.spring_boot_drill.api.dtos.request.SubmissionRequest;
import com.riwi.spring_boot_drill.api.dtos.response.SubmissionResponse;
import com.riwi.spring_boot_drill.domain.entities.Submission;
import com.riwi.spring_boot_drill.domain.repositories.AssignmentRepository;
import com.riwi.spring_boot_drill.domain.repositories.UserRepository;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IAssignmentMapper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.ISubmissionMapper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IUserMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SubmissionMapper implements ISubmissionMapper {

    @Autowired
    private final ServiceHelper helper;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Autowired
    private final IUserMapper userMapper;

    @Autowired
    private final IAssignmentMapper assignmentMapper;

    @Override
    public Submission requestToEntity(SubmissionRequest request) {
        return Submission.builder()
                .content(request.getContent())
                .userId(this.helper.find(request.getUserId(), userRepository, "user"))
                .assignmentId(this.helper.find(request.getAssignmentId(), assignmentRepository, "assignment"))
                .build();
    }

    @Override
    public SubmissionResponse entityToResponse(Submission entity) {
        return SubmissionResponse.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .date(entity.getDate())
                .grade(entity.getGrade())
                .userId(this.userMapper.entityToResponse(entity.getUserId()))
                .assignmentId(this.assignmentMapper.entityToResponse(entity.getAssignmentId()))
                .build();
    }
    
}
