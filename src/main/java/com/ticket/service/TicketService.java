package com.ticket.service;

import com.ticket.domain.Ticket;
import com.ticket.domain.Trip;
import com.ticket.dto.TicketInfoDto;
import com.ticket.dto.TicketPaymentDto;

public interface TicketService {
    Ticket buyTicket(TicketPaymentDto ticketPaymentDto);

    TicketInfoDto getTicketInfo(Long ticketId);
}
