package com.ticket.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "trip")
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

    public void increaseAvailableTickets() {
        this.availableTickets++;
    }

    public void decreaseAvailableTickets() {
        this.availableTickets--;
    }
}
