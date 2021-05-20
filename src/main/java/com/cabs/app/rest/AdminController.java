package com.cabs.app.rest;

import com.cabs.app.model.Cab;
import com.cabs.app.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cab")
public class AdminController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Object> registerCab(@RequestBody Cab cab) {
        registrationService.registerNewCab(cab);
        return ResponseEntity.status(HttpStatus.CREATED).body(cab.getId());
    }

}
