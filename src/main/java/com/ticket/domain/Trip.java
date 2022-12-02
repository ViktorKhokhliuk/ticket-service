package com.ticket.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Trip {

    @Id
    private Long id;

    @Column("departure_station")
    private String departureStation;

    @Column("arrival_station")
    private String arrivalStation;

    @Column("departure_date")
    private LocalDate departureDate;

    @Column("departure_time")
    private LocalTime departureTime;

    @Column("price")
    private double price;

    @Column("available_tickets")
    private int availableTickets;
}
