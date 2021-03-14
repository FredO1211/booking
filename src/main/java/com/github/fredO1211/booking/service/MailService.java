package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.service.dto.MessageContentDTO;
import com.github.fredO1211.booking.service.dto.MessageDTO;

public interface MailService {
    void send(MessageDTO message);
    void sendToAll(MessageContentDTO messageContentDTO);
    void sendToCurrent(Long guestId, MessageContentDTO messageContentDTO);

}
