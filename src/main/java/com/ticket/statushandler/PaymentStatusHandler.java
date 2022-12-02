package com.ticket.statushandler;

import com.ticket.domain.Payment;

public interface PaymentStatusHandler {

    void handle(Payment payment);
}
