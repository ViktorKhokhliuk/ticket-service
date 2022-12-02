package com.ticket.statushandler.impl;

import com.ticket.domain.Payment;
import com.ticket.statushandler.PaymentStatusHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component("DONE")
public class DonePaymentStatusHandler implements PaymentStatusHandler {

    @Override
    public void handle(Payment payment) {
        log.info("Status is " + payment.getStatus());
    }
}
