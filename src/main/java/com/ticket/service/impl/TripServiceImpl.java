package com.ticket.service.impl;

import com.ticket.domain.Trip;
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
}
