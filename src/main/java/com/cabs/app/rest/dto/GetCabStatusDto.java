package com.cabs.app.rest.dto;

import lombok.Data;

@Data
public class GetCabStatusDto {

    Long cabId;
    String cabStatus;
    String city;

}
