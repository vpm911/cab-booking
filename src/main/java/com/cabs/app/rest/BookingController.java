package com.cabs.app.rest;

import com.cabs.app.rest.dto.BookingRequestDto;
import com.cabs.app.rest.dto.BookingResponseDto;
import com.cabs.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<Object> requestCab(@RequestBody BookingRequestDto dto) {
        BookingResponseDto responseDto = bookingService.bookCab(dto);
        return ResponseEntity.ok().body(responseDto);
    }
}
