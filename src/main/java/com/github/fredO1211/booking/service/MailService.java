package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.service.dto.MessageDTO;

public interface MailService {
    void send(MessageDTO message);
    void sendToAll(MessageDTO messageDTO);
}
