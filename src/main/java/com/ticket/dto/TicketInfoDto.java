package com.ticket.dto;

import com.ticket.domain.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public class TicketInfoDto {

    private final String departureStation;
    private final String arrivalStation;
    private final LocalDate departureDate;
    private final LocalTime departureTime;
    private final Double price;
    private final Integer availableTickets;
    private final PaymentStatus paymentStatus;
}
