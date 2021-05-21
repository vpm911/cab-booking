package com.cabs.app.service;

import com.cabs.app.model.Booking;
import com.cabs.app.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsightsService {

    @Autowired
    BookingRepo bookingRepo;

    public Double getIdleTimeByCabId(long cabId, Instant startTime, Instant endTime) {
        List<Booking> bookings = bookingRepo.findByCabId(cabId);

        List<Booking> bookingsBetween = bookings.stream().filter(booking -> {
            if(startTime.isBefore(booking.getStartTime()) && endTime.isAfter(booking.getEndTime())){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Double tripTime = bookingsBetween.stream().map(booking -> {
               long diff = booking.getEndTime().getEpochSecond()-booking.getStartTime().getEpochSecond();
               return diff;
        }).mapToDouble(value -> Double.valueOf(value)).sum()/60/60;
        Double totalTime = (endTime.getEpochSecond()-startTime.getEpochSecond())/60.0/60.0;
        return totalTime - tripTime;
    }
}
