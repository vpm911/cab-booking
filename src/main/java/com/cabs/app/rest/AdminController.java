package com.cabs.app.rest;

import com.cabs.app.model.Cab;
import com.cabs.app.model.City;
import com.cabs.app.rest.dto.CreateCabDto;
import com.cabs.app.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/cab/register")
    public ResponseEntity<Object> registerCab(@RequestBody @Valid CreateCabDto createCabDto) {
        Cab cab = registrationService.registerNewCab(createCabDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cab.getId());
    }

    @PostMapping("/city/register")
    public ResponseEntity<Object> registerCity(@RequestParam @NotEmpty String cityName) {
        City city = registrationService.registerNewCity(cityName);
        return ResponseEntity.status(HttpStatus.CREATED).body(city.getId());
    }

}
