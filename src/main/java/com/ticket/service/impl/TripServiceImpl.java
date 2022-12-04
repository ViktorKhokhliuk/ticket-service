package com.ticket.service.impl;

import com.ticket.domain.Trip;
import com.ticket.exception.EntityNotFoundException;
import com.ticket.exception.NoAvailableTicketsException;
import com.ticket.repository.TripRepository;
import com.ticket.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    @Override
    public Iterable<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip findById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("trip with id " + tripId + " not found"));
    }

    @Override
    public Trip decreaseAvailableTickets(Long tripId) {
        Trip trip = findById(tripId);
        trip.decreaseAvailableTickets();
        if (trip.getAvailableTickets() < 0) {
            throw new NoAvailableTicketsException("there are no available tickets");
        }
        return tripRepository.save(trip);
    }

    @Override
    public Trip increaseAvailableTickets(Long tripId) {
        Trip trip = findById(tripId);
        trip.increaseAvailableTickets();
        return tripRepository.save(trip);
    }
}
