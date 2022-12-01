package com.ticket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Long paymentId;
    private Long tripId;
}
