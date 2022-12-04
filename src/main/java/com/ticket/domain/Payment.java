package com.ticket.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Payment {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Double sum;
    private PaymentStatus status;
}
