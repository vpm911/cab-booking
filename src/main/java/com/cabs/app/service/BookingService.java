package com.cabs.app.service;

import com.cabs.app.model.Cab;
import com.cabs.app.rest.dto.BookingRequestDto;
import com.cabs.app.rest.dto.BookingResponseDto;
import com.cabs.app.service.logic.MatchingLogic;
import com.cabs.app.service.logic.strategy.Strategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    MatchingLogic matchingLogic;

    public BookingResponseDto bookCab(BookingRequestDto dto) {
        Cab cab = matchingLogic.matchBookingRequest(dto, Strategies.LOCATION_IDLE_RANDOM);
        BookingResponseDto responseDto = new BookingResponseDto();
        responseDto.setLicenseNumber(cab.getLicenseNumber());
        return responseDto;
    }


}
