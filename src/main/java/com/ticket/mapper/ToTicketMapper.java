package com.ticket.mapper;

import com.ticket.domain.Ticket;
import com.ticket.dto.PaymentCreatingDto;
import org.springframework.stereotype.Component;

@Component
public class ToTicketMapper {

    public Ticket map(PaymentCreatingDto dto, Long tripId, Long paymentId) {
        Ticket ticket = new Ticket();
        ticket.setFirstName(dto.getFirstName());
        ticket.setLastName(dto.getLastName());
        ticket.setPatronymic(dto.getPatronymic());
        ticket.setTripId(tripId);
        ticket.setPaymentId(paymentId);
        return ticket;
    }
}
