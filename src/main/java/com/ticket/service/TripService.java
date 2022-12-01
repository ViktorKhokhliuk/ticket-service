package com.ticket.service;

import com.ticket.domain.Trip;

public interface TripService {

    Iterable<Trip> findAll();
}
