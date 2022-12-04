package com.ticket.service.impl;

import com.ticket.AbstractUnitTest;
import com.ticket.client.PaymentService;
import com.ticket.domain.Payment;
import com.ticket.domain.Ticket;
import com.ticket.domain.Trip;
import com.ticket.dto.PaymentCreatingDto;
import com.ticket.dto.ResponsePaymentIdDto;
import com.ticket.dto.TicketCreatingDto;
import com.ticket.dto.TicketInfoDto;
import com.ticket.exception.EntityNotFoundException;
import com.ticket.mapper.ToPaymentCreatingDtoMapper;
import com.ticket.mapper.ToTicketInfoDtoMapper;
import com.ticket.mapper.ToTicketMapper;
import com.ticket.repository.TicketRepository;
import com.ticket.service.TripService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TicketServiceImplTest extends AbstractUnitTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TripService tripService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private ToTicketMapper ticketMapper;
    @Mock
    private ToTicketInfoDtoMapper ticketInfoDtoMapper;
    @Mock
    private ToPaymentCreatingDtoMapper paymentDtoMapper;
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void getTicketInfoShouldReturnTicketInfoDtoWhenTicketExists() {
        //GIVEN
        Ticket ticket = createTicket();
        Trip trip = createTrip();
        Payment payment = createPayment();
        TicketInfoDto expectedTicketInfo = createTicketInfoDto();

        when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        when(tripService.findById(ticket.getTripId())).thenReturn(trip);
        when(paymentService.findById(ticket.getPaymentId())).thenReturn(payment);
        when(ticketInfoDtoMapper.map(trip, payment.getStatus())).thenReturn(expectedTicketInfo);

        //WHEN
        TicketInfoDto resultTicketInfo = ticketService.getTicketInfo(ticket.getId());

        //THEN
        assertEquals(expectedTicketInfo, resultTicketInfo);
    }

    @Test
    void getTicketInfoShouldThrowEntityNotFoundExceptionWhenTicketDoesNotExist() {
        //GIVEN, WHEN
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.empty());

        //THEN
        assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketInfo(anyLong()));
    }

    @Test
    void findByPaymentIdShouldReturnTicket() {
        //GIVEN
        Ticket expectedTicket = createTicket();

        when(ticketRepository.findByPaymentId(expectedTicket.getPaymentId())).thenReturn(expectedTicket);

        //WHEN
        Ticket resultTicket = ticketService.findByPaymentId(expectedTicket.getPaymentId());

        //THEN
        assertEquals(expectedTicket, resultTicket);
    }

    @Test
    void buyTicketShouldReturnNewTicket() {
        //GIVEN
        Ticket expectedTicket = createTicket();
        TicketCreatingDto ticketCreatingDto = createTicketCreatingDto();
        Trip trip = createTrip();
        PaymentCreatingDto paymentCreatingDto = createPaymentCreatingDto();
        ResponsePaymentIdDto responsePaymentIdDto = new ResponsePaymentIdDto();
        responsePaymentIdDto.setPaymentId(expectedTicket.getPaymentId());

        when(tripService.decreaseAvailableTickets(ticketCreatingDto.getTripId())).thenReturn(trip);
        when(paymentDtoMapper.map(ticketCreatingDto, trip.getPrice())).thenReturn(paymentCreatingDto);
        when(paymentService.payForTicket(paymentCreatingDto)).thenReturn(responsePaymentIdDto);
        when(ticketMapper.map(paymentCreatingDto, trip.getId(), responsePaymentIdDto.getPaymentId())).thenReturn(expectedTicket);
        when(ticketRepository.save(expectedTicket)).thenReturn(expectedTicket);

        //WHEN
        Ticket resultTicket = ticketService.buyTicket(ticketCreatingDto);

        //THEN
        assertEquals(expectedTicket, resultTicket);
    }
}