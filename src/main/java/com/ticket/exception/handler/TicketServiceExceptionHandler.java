package com.ticket.exception.handler;

import com.ticket.dto.ErrorResponseDto;
import com.ticket.exception.EntityNotFoundException;
import com.ticket.exception.NoAvailableTicketsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Log4j2
@RestControllerAdvice
public class TicketServiceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleException(EntityNotFoundException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage(), LocalDateTime.now());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleException(NoAvailableTicketsException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage(), LocalDateTime.now());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError().build();
    }
}
