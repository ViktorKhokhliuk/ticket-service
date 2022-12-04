package com.ticket.statushandler.impl;

import com.ticket.domain.Payment;
import com.ticket.statushandler.PaymentStatusHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DonePaymentStatusHandler implements PaymentStatusHandler {

    @Override
    public Payment handle(Payment payment) {
        log.info("Status is " + payment.getStatus());
        return payment;
    }
}
