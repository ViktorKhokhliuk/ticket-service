package com.ticket.service.impl;

import com.ticket.client.PaymentService;
import com.ticket.domain.Payment;
import com.ticket.domain.Ticket;
import com.ticket.domain.Trip;
import com.ticket.dto.TicketCreatingDto;
import com.ticket.dto.TicketInfoDto;
import com.ticket.dto.PaymentCreatingDto;
import com.ticket.exception.EntityNotFoundException;
import com.ticket.mapper.ToPaymentCreatingDtoMapper;
import com.ticket.mapper.ToTicketMapper;
import com.ticket.mapper.ToTicketInfoDtoMapper;
import com.ticket.repository.TicketRepository;
import com.ticket.service.TicketService;
import com.ticket.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;
    private final TripService tripService;
    private final ToTicketMapper ticketMapper;
    private final ToPaymentCreatingDtoMapper paymentDtoMapper;
    private final ToTicketInfoDtoMapper ticketInfoDtoMapper;

    @Override
    @Transactional
    public Ticket buyTicket(TicketCreatingDto ticketCreatingDto) {
        Trip updatedTrip = tripService.decreaseAvailableTickets(ticketCreatingDto.getTripId());
        PaymentCreatingDto paymentCreatingDto = paymentDtoMapper.map(ticketCreatingDto, updatedTrip.getPrice());
        Long paymentId = paymentService.payForTicket(paymentCreatingDto).getPaymentId();
        Ticket ticket = ticketMapper.map(paymentCreatingDto, updatedTrip.getId(), paymentId);
        return ticketRepository.save(ticket);
    }

    @Override
    public TicketInfoDto getTicketInfo(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("ticket with id " + ticketId + " not found"));
        Trip trip = tripService.findById(ticket.getTripId());
        Payment payment = paymentService.findById(ticket.getPaymentId());
        return ticketInfoDtoMapper.map(trip, payment.getStatus());
    }

    @Override
    public Ticket findByPaymentId(Long paymentId) {
        return ticketRepository.findByPaymentId(paymentId);
    }
}
