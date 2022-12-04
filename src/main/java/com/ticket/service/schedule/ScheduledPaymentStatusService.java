package com.ticket.service.schedule;

import com.ticket.client.PaymentService;
import com.ticket.domain.Payment;
import com.ticket.statushandler.PaymentStatusHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.ticket.domain.PaymentStatus.DONE;
import static com.ticket.domain.PaymentStatus.NEW;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduledPaymentStatusService {
    private final PaymentService paymentService;
    private final Map<String, PaymentStatusHandler> handlerMap;

    @Scheduled(fixedDelayString = "${fixedDelaySeconds}000")
    @Transactional
    public void handleTicketsByPaymentStatus() {
        log.info("Scheduled");
        List<Payment> updatedPayments = paymentService.findAllByStatus(NEW)
                .stream()
                .peek(payment -> payment.setStatus(paymentService.findPaymentStatusById(payment.getId())))
                .toList();

        updatedPayments.forEach(payment -> handlerMap.getOrDefault(payment.getStatus().name(), handlerMap.get(DONE.name())).handle(payment));
        paymentService.updateAll(updatedPayments);
    }
}
