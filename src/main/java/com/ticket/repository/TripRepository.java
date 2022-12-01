package com.ticket.repository;

import com.ticket.domain.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {

}
