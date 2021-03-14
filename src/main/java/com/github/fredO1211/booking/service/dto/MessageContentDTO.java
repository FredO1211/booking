package com.github.fredO1211.booking.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class MessageContentDTO {
    private String title;
    private String message;

    public static MessageDTO map(MessageContentDTO messageContentDTO){
        return new MessageDTO(messageContentDTO.getTitle(),messageContentDTO.getTitle(), new ArrayList<>());
    }
}
