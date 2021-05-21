package com.cabs.app.rest.dto;

import lombok.Data;

@Data
public class UpdateCabStatusDto {
    Long cabId;
    String cabState;
    String city;
}
