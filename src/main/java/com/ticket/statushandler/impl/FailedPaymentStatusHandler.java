package com.ticket.statushandler.impl;

import com.ticket.domain.Payment;
import com.ticket.domain.Ticket;
import com.ticket.service.TicketService;
import com.ticket.service.TripService;
import com.ticket.statushandler.PaymentStatusHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FailedPaymentStatusHandler implements PaymentStatusHandler {
    private final TicketService ticketService;
    private final TripService tripService;

    @Override
    public Payment handle(Payment payment) {
        Ticket ticket = ticketService.findByPaymentId(payment.getId());
        tripService.increaseAvailableTickets(ticket.getTripId());
        return payment;
    }
}
