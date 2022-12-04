package com.ticket.statushandler.impl;

import com.ticket.AbstractUnitTest;
import com.ticket.domain.Payment;
import com.ticket.domain.Ticket;
import com.ticket.service.TicketService;
import com.ticket.service.TripService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FailedPaymentStatusHandlerTest extends AbstractUnitTest {
    @Mock
    private TicketService ticketService;
    @Mock
    private TripService tripService;
    @InjectMocks
    private FailedPaymentStatusHandler statusHandler;

    @Test
    void handleShouldUpdateAvailableTickets() {
        //GIVEN
        Payment payment = createPayment();
        Ticket ticket = createTicket();

        when(ticketService.findByPaymentId(payment.getId())).thenReturn(ticket);

        //WHEN
        statusHandler.handle(payment);

        //THEN
        verify(tripService).increaseAvailableTickets(ticket.getTripId());
        verify(ticketService).findByPaymentId(payment.getId());
    }
}