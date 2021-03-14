package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.service.MailService;
import com.github.fredO1211.booking.service.dto.MessageContentDTO;
import com.github.fredO1211.booking.service.dto.MessageDTO;
import com.github.fredO1211.booking.service.impl.EmailServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/message")
public class EmailController {

    private final MailService mailService;

    public EmailController(EmailServiceImpl mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    void sendMessageToAllGuests(@RequestBody MessageContentDTO messageContentDTO){
        mailService.sendToAll(messageContentDTO);
    }

    @PostMapping(params = {"/guest_id"})
    void sendMessageToCurrentGuest( @RequestParam Long guestId,@RequestBody MessageContentDTO messageContentDTO){

    }
}
