package com.ticket.client;

import com.ticket.domain.PaymentStatus;
import com.ticket.dto.ResponsePaymentIdDto;
import com.ticket.dto.TicketPaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final RestTemplate restTemplate;

    @Override
    public PaymentStatus getPaymentStatus(Long paymentId) {
        return PaymentStatus.NEW;
    }

    @Override
    public ResponsePaymentIdDto payForTicket(TicketPaymentDto ticketPaymentDto) {
        return null;
    }
}
