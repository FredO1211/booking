package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.service.GuestService;
import com.github.fredO1211.booking.service.MailService;
import com.github.fredO1211.booking.service.dto.MessageContentDTO;
import com.github.fredO1211.booking.service.dto.MessageDTO;
import com.github.fredO1211.booking.service.exceptions.EntityNotFoundException;
import com.github.fredO1211.booking.service.exceptions.NoMailsFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements MailService {

    private final RabbitTemplate rabbitTemplate;
    private final GuestService guestService;

    public EmailServiceImpl(RabbitTemplate rabbitTemplate, GuestServiceImpl guestService) {
        this.rabbitTemplate = rabbitTemplate;
        this.guestService = guestService;
    }

    @Override
    public void send(MessageDTO message) {
        rabbitTemplate.convertAndSend("mail", message);
    }

    @Override
    public void sendToAll(MessageContentDTO messageContentDTO) {
        var messageDTO = MessageContentDTO.map(messageContentDTO);
        List<Guest> mails = guestService.getAll();
        fillMailList(messageDTO, mails);
        rabbitTemplate.convertAndSend("mail",messageDTO);
    }

    private void fillMailList(MessageDTO messageDTO, List<Guest> mails) {
        if (mails.isEmpty()) {
            throw new NoMailsFoundException();
        }
        messageDTO.setEmails(mails.stream()
                .map(Guest::getEmail).collect(Collectors.toList()));
    }

    @Override
    public void sendToCurrent(Long guestId, MessageContentDTO messageContentDTO) {
        var messageDTO = MessageContentDTO.map(messageContentDTO);
        List<Guest> mails = Collections
                .singletonList(guestService.getById(guestId)
                        .orElseThrow(EntityNotFoundException::new));
        fillMailList(messageDTO, mails);
        rabbitTemplate.convertAndSend("mail",messageDTO);
    }
}
