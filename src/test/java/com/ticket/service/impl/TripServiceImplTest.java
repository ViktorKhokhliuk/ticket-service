package com.ticket.service.impl;

import com.ticket.AbstractUnitTest;
import com.ticket.domain.Trip;
import com.ticket.exception.EntityNotFoundException;
import com.ticket.exception.NoAvailableTicketsException;
import com.ticket.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TripServiceImplTest extends AbstractUnitTest {
    @Mock
    private TripRepository tripRepository;
    @InjectMocks
    private TripServiceImpl tripService;

    @Test
    void findByIdShouldReturnTripWhenItExist() {
        Trip expectedTrip = createTrip();

        when(tripRepository.findById(expectedTrip.getId())).thenReturn(Optional.of(expectedTrip));

        Trip resultTrip = tripService.findById(expectedTrip.getId());

        assertEquals(expectedTrip, resultTrip);
    }

    @Test
    void findByIdShouldThrowEntityNotFoundExceptionWhenTripDoesNotExist() {
        when(tripRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> tripService.findById(anyLong()));
    }

    @Test
    void decreaseAvailableTickets() {
        Trip trip = createTrip();
        int availableTicketsBeforeDecrease = trip.getAvailableTickets();

        when(tripRepository.findById(trip.getId())).thenReturn(Optional.of(trip));
        when(tripRepository.save(trip)).thenReturn(trip);

        Trip updatedTrip = tripService.decreaseAvailableTickets(trip.getId());

        assertTrue(availableTicketsBeforeDecrease > updatedTrip.getAvailableTickets());
        assertEquals(1, availableTicketsBeforeDecrease - updatedTrip.getAvailableTickets());
    }

    @Test
    void decreaseAvailableTicketsShouldThrowNoAvailableTicketsException() {
        Trip trip = createTrip();
        trip.setAvailableTickets(0);

        when(tripRepository.findById(trip.getId())).thenReturn(Optional.of(trip));

        assertThrows(NoAvailableTicketsException.class, () -> tripService.decreaseAvailableTickets(trip.getId()));
    }

    @Test
    void increaseAvailableTickets() {
        Trip trip = createTrip();
        int availableTicketsBeforeIncrease = trip.getAvailableTickets();

        when(tripRepository.findById(trip.getId())).thenReturn(Optional.of(trip));
        when(tripRepository.save(trip)).thenReturn(trip);

        Trip updatedTrip = tripService.increaseAvailableTickets(trip.getId());

        assertTrue(availableTicketsBeforeIncrease < updatedTrip.getAvailableTickets());
        assertEquals(1, updatedTrip.getAvailableTickets() - availableTicketsBeforeIncrease);
    }
}