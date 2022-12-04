package com.ticket.client;

import com.ticket.domain.Payment;
import com.ticket.domain.PaymentStatus;
import com.ticket.dto.ResponsePaymentIdDto;
import com.ticket.dto.PaymentCreatingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final RestTemplate restTemplate;
    @Value("${paymentService.url}")
    private String basePaymentServiceUrl;

    @Override
    public PaymentStatus findPaymentStatusById(Long paymentId) {
        return restTemplate.getForObject(basePaymentServiceUrl + "/status/" + paymentId, PaymentStatus.class);

    }

    @Override
    public ResponsePaymentIdDto payForTicket(PaymentCreatingDto paymentCreatingDto) {
        return restTemplate.postForObject(basePaymentServiceUrl, paymentCreatingDto, ResponsePaymentIdDto.class);
    }

    @Override
    public Payment findById(Long paymentId) {
        return restTemplate.getForObject(basePaymentServiceUrl + "/" + paymentId, Payment.class);
    }

    @Override
    public List<Payment> findAllByStatus(PaymentStatus status) {
        Payment[] payments = restTemplate.getForEntity(basePaymentServiceUrl + "/all/" + status, Payment[].class).getBody();
        return payments != null ? Arrays.asList(payments) : Collections.emptyList();
    }

    @Override
    public void updateAll(List<Payment> payments) {
        restTemplate.put(basePaymentServiceUrl, payments);
    }
}
