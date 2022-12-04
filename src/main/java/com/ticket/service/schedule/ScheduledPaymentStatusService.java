package com.ticket.service.schedule;

import com.ticket.client.PaymentService;
import com.ticket.domain.Payment;
import com.ticket.domain.PaymentStatus;
import com.ticket.statushandler.PaymentStatusHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.ticket.domain.PaymentStatus.NEW;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduledPaymentStatusService {
    private final PaymentService paymentService;
    private final Map<PaymentStatus, PaymentStatusHandler> handlerMap;

    @Scheduled(fixedDelayString = "${fixedDelay}")
    @Transactional
    public void handleTicketsByPaymentStatus() {
        log.info("Payment processing");
        List<Payment> updatedPayments = paymentService.findAllByStatus(NEW)
                .stream()
                .map(this::updateStatus)
                .map(payment -> handlerMap.get(payment.getStatus()).handle(payment))
                .toList();

        paymentService.updateAll(updatedPayments);
    }

    private Payment updateStatus(Payment payment) {
        PaymentStatus paymentStatusById = paymentService.findPaymentStatusById(payment.getId());
        payment.setStatus(paymentStatusById);
        return payment;
    }
}
