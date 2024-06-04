package com.riwi.spring_boot_drill.infrastructure.helpers.abstract_mappers;

import com.riwi.spring_boot_drill.api.dtos.request.MessageRequest;
import com.riwi.spring_boot_drill.api.dtos.response.MessageResponse;
import com.riwi.spring_boot_drill.domain.entities.Message;

public interface IMessageMapper
        extends MapperBase<MessageRequest, Message, MessageResponse> {

}
