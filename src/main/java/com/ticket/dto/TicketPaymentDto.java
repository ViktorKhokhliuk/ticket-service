package com.ticket.dto;

import lombok.Data;

@Data
public class TicketPaymentDto {

    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Long tripId;
}
