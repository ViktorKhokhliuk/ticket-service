package com.ticket.mapper;

import com.ticket.domain.PaymentStatus;
import com.ticket.domain.Trip;
import com.ticket.dto.TicketInfoDto;
import org.springframework.stereotype.Component;

@Component
public class ToTicketInfoDtoMapper {

    public TicketInfoDto map(Trip trip, PaymentStatus paymentStatus) {
        return TicketInfoDto.builder()
                .departureStation(trip.getDepartureStation())
                .arrivalStation(trip.getArrivalStation())
                .departureDate(trip.getDepartureDate())
                .departureTime(trip.getDepartureTime())
                .price(trip.getPrice())
                .availableTickets(trip.getAvailableTickets())
                .paymentStatus(paymentStatus)
                .build();
    }
}
