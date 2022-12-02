package com.ticket.dto;

import lombok.Data;

@Data
public class TicketCreatingDto {

    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Long tripId;
}
