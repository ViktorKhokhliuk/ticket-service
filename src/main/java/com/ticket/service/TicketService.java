package com.ticket.service;

import com.ticket.domain.Ticket;
import com.ticket.dto.TicketCreatingDto;
import com.ticket.dto.TicketInfoDto;

public interface TicketService {
    Ticket buyTicket(TicketCreatingDto ticketCreatingDto);

    TicketInfoDto getTicketInfo(Long ticketId);

    Ticket findByPaymentId(Long paymentId);
}
