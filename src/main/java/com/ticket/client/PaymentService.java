package com.ticket.client;

import com.ticket.domain.PaymentStatus;
import com.ticket.dto.ResponsePaymentIdDto;
import com.ticket.dto.TicketPaymentDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService {
    PaymentStatus getPaymentStatus(Long paymentId);

    ResponsePaymentIdDto payForTicket(TicketPaymentDto ticketPaymentDto);
}
