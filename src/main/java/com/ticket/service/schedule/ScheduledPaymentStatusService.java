package com.ticket.service.schedule;

import com.ticket.client.PaymentService;
import com.ticket.domain.Payment;
import com.ticket.statushandler.PaymentStatusHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.ticket.domain.PaymentStatus.NEW;

@Service
@RequiredArgsConstructor
public class ScheduledPaymentStatusService {
    private final PaymentService paymentService;
    private final Map<String, PaymentStatusHandler> handlerMap;

    @Scheduled(fixedDelayString = "${fixed_delay_seconds}000")
    @Transactional
    public void handleTicketsByPaymentStatus() {
        System.out.println("AAAAAAAAAAA");
        List<Payment> updatedPayments = paymentService.findAllByStatus(NEW)
                .stream()
                .peek(payment -> payment.setStatus(paymentService.getPaymentStatus(payment.getId())))
                .toList();

        updatedPayments.forEach(payment -> handlerMap.getOrDefault(payment.getStatus().name(), handlerMap.get(NEW.name())).handle(payment));
        paymentService.saveAll(updatedPayments);
    }
}
