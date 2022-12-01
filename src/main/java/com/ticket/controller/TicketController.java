package com.ticket.controller;

import com.ticket.dto.ResponseTicketIdDto;
import com.ticket.dto.TicketInfoDto;
import com.ticket.dto.TicketPaymentDto;
import com.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    public TicketInfoDto getTicketInfo(@PathVariable(name = "id") Long ticketId) {
       return ticketService.getTicketInfo(ticketId);
    }

    @PostMapping
    public ResponseTicketIdDto buyTicket(@RequestBody TicketPaymentDto ticketPaymentDto) {
        Long ticketId = ticketService.buyTicket(ticketPaymentDto).getId();
        return new ResponseTicketIdDto(ticketId);
    }
}
