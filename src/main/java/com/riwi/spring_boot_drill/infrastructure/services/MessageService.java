package com.riwi.spring_boot_drill.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.spring_boot_drill.api.dtos.request.MessageRequest;
import com.riwi.spring_boot_drill.api.dtos.response.MessageResponse;
import com.riwi.spring_boot_drill.domain.entities.Message;
import com.riwi.spring_boot_drill.domain.repositories.MessageRepository;
import com.riwi.spring_boot_drill.infrastructure.abstract_services.IMessageService;
import com.riwi.spring_boot_drill.infrastructure.helpers.ServiceHelper;
import com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers.IMessageMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {
    
     @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    private final ServiceHelper serviceHelper;

    @Autowired
    private final IMessageMapper messageMapper;

    @Override
    public MessageResponse create(MessageRequest request) {
        Message message = this.messageMapper.requestToEntity(request);
        return this.messageMapper.entityToResponse(this.messageRepository.save(message));
    }

    @Override
    public MessageResponse get(Long id) {
        Message message = this.serviceHelper.find(id, messageRepository, "message");
        return this.messageMapper.entityToResponse(message);
    }

    @Override
    public Page<MessageResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        if (size < 1) size = 10;

        return this.messageRepository.findAll(PageRequest.of(page, size))
                .map(this.messageMapper::entityToResponse);
    }

    @Override
    public void delete(Long id) {
        Message message = this.serviceHelper.find(id, messageRepository, "message");
        this.messageRepository.delete(message);
    }
}
