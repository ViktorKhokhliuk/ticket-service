package com.ticket.service;

import com.ticket.domain.Trip;

public interface TripService {

    Iterable<Trip> findAll();

    Trip decreaseAvailableTickets(Long tripId);

    Trip increaseAvailableTickets(Long tripId);

    Trip findById(Long tripId);
}
