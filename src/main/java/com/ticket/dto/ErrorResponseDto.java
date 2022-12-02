package com.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {

    private String message;
    private LocalDateTime timeStamp;
}
