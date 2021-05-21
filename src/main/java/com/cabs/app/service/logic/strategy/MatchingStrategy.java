package com.cabs.app.service.logic.strategy;

import com.cabs.app.model.Cab;
import com.cabs.app.rest.dto.BookingRequestDto;

/**
 * Strategy interface that will be used to findCab based on requirement
 */
public interface MatchingStrategy {

    Cab findCab(BookingRequestDto requestDto);
}
