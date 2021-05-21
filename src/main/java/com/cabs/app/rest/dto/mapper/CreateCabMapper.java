package com.cabs.app.rest.dto.mapper;

import com.cabs.app.model.Cab;
import com.cabs.app.repo.CityRepo;
import com.cabs.app.rest.dto.CreateCabDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCabMapper{

    @Autowired
    CityRepo cityRepo;

    public Cab toEntity(CreateCabDto cabDto) {
        Cab cab = new Cab();
        cab.setCurrentCity(cityRepo.findByName(cabDto.getCurrentCity()).get());
        return cab;
    }
}
