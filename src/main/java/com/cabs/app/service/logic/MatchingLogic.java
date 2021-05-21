package com.cabs.app.service.logic;

import com.cabs.app.model.Cab;
import com.cabs.app.rest.dto.BookingRequestDto;
import com.cabs.app.service.logic.strategy.LocationIdleRandomStrategy;
import com.cabs.app.service.logic.strategy.MatchingStrategy;
import com.cabs.app.service.logic.strategy.Strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Takes in strategy name and invokes the respective strategy
 */
@Component
public class MatchingLogic {

    @Autowired
    ApplicationContext context;

    public Cab matchBookingRequest(BookingRequestDto dto, String strategy ) {
        if(strategy.equals(Strategies.LOCATION_IDLE_RANDOM)) {
            MatchingStrategy matchingStrategy = (MatchingStrategy) context.getBean(strategy);
            return matchingStrategy.findCab(dto);
        }
        throw new UnsupportedOperationException("Invalid Strategy");
    }
}
