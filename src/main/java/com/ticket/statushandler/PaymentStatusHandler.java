package com.ticket.statushandler;

import com.ticket.domain.Payment;

public interface PaymentStatusHandler {

    Payment handle(Payment payment);
}
