package com.cabs.app.service.logic.strategy;

import com.cabs.app.model.Cab;
import com.cabs.app.rest.dto.BookingRequestDto;

public interface MatchingStrategy {

    Cab findCab(BookingRequestDto requestDto);
}
