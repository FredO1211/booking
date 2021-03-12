package com.github.fredO1211.booking.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO {
    String title;
    String message;
    List<String> emails;
}
