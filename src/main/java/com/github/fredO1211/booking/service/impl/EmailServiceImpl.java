package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.service.GuestService;
import com.github.fredO1211.booking.service.MailService;
import com.github.fredO1211.booking.service.dto.MessageDTO;
import com.github.fredO1211.booking.service.exceptions.NoMailsFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
    public void sendToAll(MessageDTO messageDTO) {
        List<Guest> mails = guestService.getAll();
        if (mails.isEmpty()) {
            throw new NoMailsFoundException();
        }
        messageDTO.setEmails(mails.stream()
                .map(Guest::getEmail).collect(Collectors.toList()));
        rabbitTemplate.convertAndSend("mail",messageDTO);
    }
}
