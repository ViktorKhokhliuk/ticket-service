package com.ticket.mapper;

import com.ticket.domain.Ticket;
import com.ticket.dto.TicketPaymentDto;
import org.springframework.stereotype.Component;

@Component
public class TicketPaymentDtoToTicketMapper {

    public Ticket map(TicketPaymentDto dto) {
        Ticket ticket = new Ticket();
        ticket.setFirstName(dto.getFirstName());
        ticket.setLastName(dto.getLastName());
        ticket.setPatronymic(dto.getPatronymic());
        return ticket;
    }
}
