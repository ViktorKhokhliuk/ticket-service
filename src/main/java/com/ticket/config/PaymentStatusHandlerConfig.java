package com.ticket.config;

import com.ticket.domain.PaymentStatus;
import com.ticket.service.TicketService;
import com.ticket.service.TripService;
import com.ticket.statushandler.PaymentStatusHandler;
import com.ticket.statushandler.impl.DonePaymentStatusHandler;
import com.ticket.statushandler.impl.FailedPaymentStatusHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentStatusHandlerConfig {

    @Bean
    public Map<PaymentStatus, PaymentStatusHandler> paymentStatusHandlerMap(
            FailedPaymentStatusHandler failedPaymentStatusHandler,
            DonePaymentStatusHandler donePaymentStatusHandler) {

        return Map.of(
                PaymentStatus.FAILED, failedPaymentStatusHandler,
                PaymentStatus.DONE, donePaymentStatusHandler,
                PaymentStatus.NEW, payment -> payment
        );
    }

    @Bean
    public FailedPaymentStatusHandler failedPaymentStatusHandler(TicketService ticketService, TripService tripService) {
        return new FailedPaymentStatusHandler(ticketService, tripService);
    }

    @Bean
    public DonePaymentStatusHandler donePaymentStatusHandler() {
        return new DonePaymentStatusHandler();
    }
}
