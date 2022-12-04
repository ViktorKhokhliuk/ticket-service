package com.ticket.repository;

import com.ticket.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Ticket findByPaymentId(Long paymentId);
}
