package com.ticket.client;

import com.ticket.domain.Payment;
import com.ticket.domain.PaymentStatus;
import com.ticket.dto.ResponsePaymentIdDto;
import com.ticket.dto.PaymentCreatingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final RestTemplate restTemplate;

    @Override
    public PaymentStatus getPaymentStatus(Long paymentId) {
        return restTemplate.getForObject("localhost:8081/payment/status/" + paymentId, PaymentStatus.class);
    }

    @Override
    public ResponsePaymentIdDto payForTicket(PaymentCreatingDto paymentCreatingDto) {
        return restTemplate.postForObject("localhost:8081/payment", paymentCreatingDto, ResponsePaymentIdDto.class);
    }

    @Override
    public Payment findById(Long paymentId) {
        return null;
    }

    @Override
    public List<Payment> findAllByStatus(PaymentStatus status) {
        return null;
    }

    @Override
    public void saveAll(List<Payment> payments) {

    }
}
