package com.ticket.dto;

import lombok.Data;

@Data
public class PaymentCreatingDto {

    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Double sum;
}
