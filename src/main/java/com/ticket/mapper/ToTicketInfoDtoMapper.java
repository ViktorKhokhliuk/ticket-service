package com.ticket.mapper;

import com.ticket.domain.PaymentStatus;
import com.ticket.domain.Trip;
import com.ticket.dto.TicketInfoDto;
import org.springframework.stereotype.Component;

@Component
public class ToTicketInfoDtoMapper {

    public TicketInfoDto map(Trip trip, PaymentStatus paymentStatus) {
        return new TicketInfoDto(
                trip.getDepartureStation(),
                trip.getArrivalStation(),
                trip.getDepartureDate(),
                trip.getDepartureTime(),
                trip.getPrice(),
                trip.getAvailableTickets(),
                paymentStatus);
    }
}
