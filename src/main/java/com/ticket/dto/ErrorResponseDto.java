package com.ticket.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

    private final String message;
    private final LocalDateTime timeStamp;
}
