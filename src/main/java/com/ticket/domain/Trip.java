package com.ticket.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Trip {

    @Id
    private Long id;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureTime;
    private double price;
    private int availableTickets;
}
