package com.cabs.app.service;

import com.cabs.app.model.Booking;
import com.cabs.app.model.Cab;
import com.cabs.app.repo.BookingRepo;
import com.cabs.app.repo.CabRepo;
import com.cabs.app.repo.CustomerRepo;
import com.cabs.app.rest.dto.BookingRequestDto;
import com.cabs.app.rest.dto.BookingResponseDto;
import com.cabs.app.service.logic.MatchingLogic;
import com.cabs.app.service.logic.strategy.Strategies;
import com.cabs.app.state.CabState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
public class BookingService {

    @Autowired
    MatchingLogic matchingLogic;

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CabRepo cabRepo;

    @Transactional
    public BookingResponseDto bookCab(BookingRequestDto dto) {
        Cab cab = matchingLogic.matchBookingRequest(dto, Strategies.LOCATION_IDLE_RANDOM);
        createBookingEntry(cab,dto);
        updateCabState(cab);
        return buildResponse(cab);
    }

    private BookingResponseDto buildResponse(Cab cab) {
        BookingResponseDto responseDto = new BookingResponseDto();
        responseDto.setLicenseNumber(cab.getLicenseNumber());
        return responseDto;
    }

    private void createBookingEntry(Cab cab, BookingRequestDto dto) {
        Booking booking = new Booking();
        booking.setCab(cab);
        booking.setCustomer(customerRepo.findById(dto.getCustomerId()).get());
        booking.setStartTime(Instant.now());
        bookingRepo.save(booking);
    }

    private void updateCabState(Cab cab){
        cab.setState(cab.getState().changeToNextState());
        cab.setLastOnTrip(Instant.now());
        cabRepo.save(cab);
    }

}
