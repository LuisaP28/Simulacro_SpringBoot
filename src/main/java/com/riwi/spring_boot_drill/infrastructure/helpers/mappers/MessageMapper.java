package com.riwi.spring_boot_drill.infrastructure.helpers.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.riwi.spring_boot_drill.api.dtos.request.MessageRequest;
import com.riwi.spring_boot_drill.api.dtos.response.MessageResponse;
import com.riwi.spring_boot_drill.domain.entities.Message;
import com.riwi.spring_boot_drill.domain.repositories.CourseRepository;
import com.riwi.spring_boot_drill.domain.repositories.UserRepository;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IMessageMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MessageMapper implements IMessageMapper{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Override
    public Message requestToEntity(MessageRequest request) {
        return Message.builder()
                .senderId(this.serviceHelper.find(request.getSenderId(), userRepository, "User Sender ID"))
                .receiverId(this.serviceHelper.find(request.getReceiverId(), userRepository, "User Reciever ID"))
                .courseId(this.serviceHelper.find(request.getCourseId(), courseRepository, "Course ID"))
                .content(request.getContent())
                .build();
    }

    @Override
    public MessageResponse entityToResponse(Message entity) {
        return MessageResponse.builder()
                .id(entity.getId())
                .senderId(null)
                .receiverId(null)
                .courseId(null)
                .content(entity.getContent())
                .date(entity.getDate())
                .build();
    }
    
}
