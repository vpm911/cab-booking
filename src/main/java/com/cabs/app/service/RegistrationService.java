package com.cabs.app.service;

import com.cabs.app.exceptions.ClientErrorException;
import com.cabs.app.exceptions.ServerErrorException;
import com.cabs.app.model.Cab;
import com.cabs.app.model.City;
import com.cabs.app.repo.CabRepo;
import com.cabs.app.repo.CityRepo;
import com.cabs.app.rest.dto.CreateCabDto;
import com.cabs.app.rest.dto.mapper.CreateCabMapper;
import com.cabs.app.state.CabState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    CabRepo cabRepo;

    @Autowired
    CityRepo cityRepo;

    @Autowired
    CreateCabMapper mapper;

    public Cab registerNewCab(CreateCabDto cabDto) {
        validateCabCreateRequest(cabDto);
        Cab cab = mapper.toEntity(cabDto);
        addDefaults(cab);
        return cabRepo.save(cab);
    }

    private void validateCabCreateRequest(CreateCabDto cabDto) {
        Optional<City> city = cityRepo.findByName(cabDto.getCurrentCity());
        if (city.isEmpty()) {
            throw new ClientErrorException("Invalid City");
        }
    }

    private void addDefaults(Cab cab) {
        cab.setState(CabState.IDLE);
        cab.setLastOnTrip(Instant.now());
    }

    public City registerNewCity(String cityName) {
        Optional<City> city = cityRepo.findByName(cityName);
        if (city.isPresent()) {
            throw new ServerErrorException("City already registered");
        }
        return cityRepo.save(new City(cityName));
    }
}
