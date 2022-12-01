package com.ticket.service.impl;

import com.ticket.client.PaymentService;
import com.ticket.domain.Ticket;
import com.ticket.domain.Trip;
import com.ticket.dto.TicketInfoDto;
import com.ticket.dto.TicketPaymentDto;
import com.ticket.mapper.TicketPaymentDtoToTicketMapper;
import com.ticket.repository.TicketRepository;
import com.ticket.repository.TripRepository;
import com.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;
    private final TripRepository tripRepository;
    private final TicketPaymentDtoToTicketMapper ticketMapper;

    @Override
    @Transactional
    public Ticket buyTicket(TicketPaymentDto ticketPaymentDto) {
        Trip trip = tripRepository.findById(ticketPaymentDto.getTripId()).orElseThrow();
        Long paymentId = paymentService.payForTicket(ticketPaymentDto).getPaymentId();
        Ticket ticket = ticketMapper.map(ticketPaymentDto);
        ticket.setTripId(trip.getId());
        ticket.setPaymentId(paymentId);
        return ticketRepository.save(ticket);
    }

    @Override
    public TicketInfoDto getTicketInfo(Long ticketId) {
        return null;
    }
}
