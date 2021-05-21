package com.cabs.app.rest.dto;

import lombok.Data;

@Data
public class TripCompleteDto {

    long bookingId;
    String licenseNumber;
    Double hours;
}
