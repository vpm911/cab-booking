package com.cabs.app.rest;

import com.cabs.app.model.Cab;
import com.cabs.app.model.City;
import com.cabs.app.rest.dto.CreateCabDto;
import com.cabs.app.rest.dto.GetCabStatusDto;
import com.cabs.app.rest.dto.UpdateCabStatusDto;
import com.cabs.app.service.BookingService;
import com.cabs.app.service.AdminService;
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
    AdminService adminService;

    @Autowired
    BookingService bookingService;

    @PostMapping("/cab/register")
    public ResponseEntity<Object> registerCab(@RequestBody @Valid CreateCabDto createCabDto) {
        Cab cab = adminService.registerNewCab(createCabDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cab.getId());
    }

    @GetMapping("/cab/status/{cabId}")
    public ResponseEntity<Object> getCabStatusById(@PathVariable long cabId) {
        GetCabStatusDto response = adminService.getCabStatusById(cabId);
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("/cab/status")
    public ResponseEntity<Object> updateStatus(@RequestBody @Valid UpdateCabStatusDto updateCabStatusDto) {
        adminService.updateCabStatusAndCity(updateCabStatusDto);
        return ResponseEntity.ok("Updated Status");
    }

    @PostMapping("/city/register")
    public ResponseEntity<Object> registerCity(@RequestParam @NotEmpty String cityName) {
        City city = adminService.registerNewCity(cityName);
        return ResponseEntity.status(HttpStatus.CREATED).body(city.getId());
    }

}
