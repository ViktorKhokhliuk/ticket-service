package com.ticket.service.schedule;

import com.ticket.AbstractUnitTest;
import com.ticket.client.PaymentService;
import com.ticket.domain.Payment;
import com.ticket.domain.PaymentStatus;
import com.ticket.statushandler.PaymentStatusHandler;
import com.ticket.statushandler.impl.DonePaymentStatusHandler;
import com.ticket.statushandler.impl.FailedPaymentStatusHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScheduledPaymentStatusServiceTest extends AbstractUnitTest {
    private PaymentService paymentService;
    private FailedPaymentStatusHandler failedPaymentStatusHandler;
    private DonePaymentStatusHandler donePaymentStatusHandler;
    private ScheduledPaymentStatusService statusService;

    @BeforeAll
    void init() {
        paymentService = mock(PaymentService.class);
        failedPaymentStatusHandler = mock(FailedPaymentStatusHandler.class);
        donePaymentStatusHandler = mock(DonePaymentStatusHandler.class);
        Map<String, PaymentStatusHandler> statusHandlerMap = Map.of(
                "FAILED", failedPaymentStatusHandler,
                "DONE", donePaymentStatusHandler);
        statusService = new ScheduledPaymentStatusService(paymentService, statusHandlerMap);
    }

    @Test
    void handleTicketsByPaymentStatus() {
        List<Payment> payments = createPaymentsWithNewStatus();

        when(paymentService.findAllByStatus(PaymentStatus.NEW)).thenReturn(payments);
        when(paymentService.findPaymentStatusById(anyLong()))
                .thenReturn(PaymentStatus.FAILED)
                .thenReturn(PaymentStatus.FAILED)
                .thenReturn(PaymentStatus.DONE);

        statusService.handleTicketsByPaymentStatus();

        verify(failedPaymentStatusHandler, times(2)).handle(any(Payment.class));
        verify(donePaymentStatusHandler, times(1)).handle(any(Payment.class));
        verify(paymentService).updateAll(anyList());
    }
}