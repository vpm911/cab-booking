package com.cabs.app.service;

import com.cabs.app.model.Cab;
import com.cabs.app.repo.CabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    CabRepo cabRepo;

    public void registerNewCab(Cab cab){
        cabRepo.save(cab);
    }

}
