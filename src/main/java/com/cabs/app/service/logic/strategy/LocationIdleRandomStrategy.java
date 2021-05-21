package com.cabs.app.service.logic.strategy;

import com.cabs.app.exceptions.ServerErrorException;
import com.cabs.app.model.Cab;
import com.cabs.app.repo.CabRepo;
import com.cabs.app.rest.dto.BookingRequestDto;
import com.cabs.app.state.CabState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * This strategy looks for same location, most idle and then random cabs.
 */
@Component("locationIdleRandomStrategy")
public class LocationIdleRandomStrategy implements MatchingStrategy {

    @Autowired
    CabRepo cabRepo;

    @Override
    public Cab findCab(BookingRequestDto requestDto) {
        List<Cab> availableCabs =
                cabRepo.findByStateAndCurrentCityNameOrderByLastOnTrip(CabState.IDLE,requestDto.getFromCity());
        if(availableCabs.isEmpty()){
            throw new ServerErrorException("No cabs available");
        }
        return availableCabs.get(new Random().nextInt(availableCabs.size()));
    }
}
