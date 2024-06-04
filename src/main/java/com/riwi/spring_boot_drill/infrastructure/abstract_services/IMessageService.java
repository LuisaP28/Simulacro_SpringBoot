package com.riwi.spring_boot_drill.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.spring_boot_drill.api.dtos.request.MessageRequest;
import com.riwi.spring_boot_drill.api.dtos.response.MessageResponse;

public interface IMessageService extends ServiceBase<MessageRequest, MessageResponse, Long> {
        MessageResponse create(MessageRequest request);
        MessageResponse get(Long id);
        Page<MessageResponse> getAll(int page, int size);
        void delete(Long id);
}
