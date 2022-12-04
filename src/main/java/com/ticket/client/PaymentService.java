package com.ticket.client;

import com.ticket.domain.Payment;
import com.ticket.domain.PaymentStatus;
import com.ticket.dto.ResponsePaymentIdDto;
import com.ticket.dto.PaymentCreatingDto;

import java.util.List;

public interface PaymentService {

    PaymentStatus findPaymentStatusById(Long paymentId);

    ResponsePaymentIdDto payForTicket(PaymentCreatingDto paymentCreatingDto);

    Payment findById(Long paymentId);

    List<Payment> findAllByStatus(PaymentStatus status);

    void updateAll(List<Payment> payments);
}
