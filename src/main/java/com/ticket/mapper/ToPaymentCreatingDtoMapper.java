package com.ticket.mapper;

import com.ticket.dto.PaymentCreatingDto;
import com.ticket.dto.TicketCreatingDto;
import org.springframework.stereotype.Component;

@Component
public class ToPaymentCreatingDtoMapper {

    public PaymentCreatingDto map(TicketCreatingDto ticketCreatingDto, Double sum) {
        return new PaymentCreatingDto(
                ticketCreatingDto.getFirstName(),
                ticketCreatingDto.getLastName(),
                ticketCreatingDto.getPatronymic(),
                sum);
    }
}
