package com.ticket;

import com.ticket.domain.Payment;
import com.ticket.domain.PaymentStatus;
import com.ticket.domain.Ticket;
import com.ticket.domain.Trip;
import com.ticket.dto.PaymentCreatingDto;
import com.ticket.dto.TicketCreatingDto;
import com.ticket.dto.TicketInfoDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AbstractUnitTest {

    protected Ticket createTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setFirstName("First Name");
        ticket.setLastName("Last Name");
        ticket.setPatronymic("Patronymic");
        ticket.setPaymentId(1L);
        ticket.setTripId(1L);
        return ticket;
    }

    protected Trip createTrip() {
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setDepartureStation("Kyiv");
        trip.setArrivalStation("Mariupol");
        trip.setDepartureDate(LocalDate.now());
        trip.setDepartureTime(LocalTime.now());
        trip.setPrice(1200D);
        trip.setAvailableTickets(10);
        return trip;
    }

    protected Payment createPayment() {
        Ticket ticket = createTicket();
        Payment payment = new Payment();
        payment.setId(ticket.getPaymentId());
        payment.setFirstName(ticket.getFirstName());
        payment.setLastName(ticket.getLastName());
        payment.setPatronymic(ticket.getPatronymic());
        payment.setSum(1200D);
        payment.setStatus(PaymentStatus.NEW);
        return payment;
    }

    protected TicketInfoDto createTicketInfoDto() {
        Trip trip = createTrip();
        return new TicketInfoDto(
                trip.getDepartureStation(),
                trip.getArrivalStation(),
                trip.getDepartureDate(),
                trip.getDepartureTime(),
                trip.getPrice(),
                trip.getAvailableTickets(),
                PaymentStatus.NEW
        );
    }

    protected TicketCreatingDto createTicketCreatingDto() {
        Ticket ticket = createTicket();
        return new TicketCreatingDto(ticket.getFirstName(), ticket.getLastName(), ticket.getPatronymic(), ticket.getTripId());
    }

    protected PaymentCreatingDto createPaymentCreatingDto() {
        Payment payment = createPayment();
        return new PaymentCreatingDto(
                payment.getFirstName(),
                payment.getLastName(),
                payment.getPatronymic(),
                payment.getSum()
        );
    }

    protected List<Payment> createPaymentsWithNewStatus() {
        Payment payment1 = createPayment();
        Payment payment2 = createPayment();
        Payment payment3 = createPayment();

        payment2.setId(2L);
        payment3.setId(3L);

        return List.of(payment1, payment2, payment3);
    }
}
