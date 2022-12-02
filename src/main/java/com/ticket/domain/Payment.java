package com.ticket.domain;

import lombok.Data;

@Data
public class Payment {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Double sum;
    private PaymentStatus status;
}
