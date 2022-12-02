package com.ticket.statushandler.impl;

import com.ticket.domain.Payment;
import com.ticket.statushandler.PaymentStatusHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component("NEW")
public class NewPaymentStatusHandler implements PaymentStatusHandler {

    @Override
    public void handle(Payment payment) {
        log.info("status is " + payment.getStatus());
    }
}
