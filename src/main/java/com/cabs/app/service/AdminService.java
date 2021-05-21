package com.cabs.app.service;

import com.cabs.app.exceptions.ClientErrorException;
import com.cabs.app.exceptions.ServerErrorException;
import com.cabs.app.model.Cab;
import com.cabs.app.model.City;
import com.cabs.app.repo.CabRepo;
import com.cabs.app.repo.CityRepo;
import com.cabs.app.rest.dto.CreateCabDto;
import com.cabs.app.rest.dto.GetCabStatusDto;
import com.cabs.app.rest.dto.UpdateCabStatusDto;
import com.cabs.app.rest.dto.mapper.CreateCabMapper;
import com.cabs.app.state.CabState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AdminService {

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

    public City registerNewCity(String cityName) {
        Optional<City> city = cityRepo.findByName(cityName);
        if (city.isPresent()) {
            throw new ServerErrorException("City already registered");
        }
        return cityRepo.save(new City(cityName));
    }

    /**
     * Method to update both state and city at the same time - for when we finish a trip
     * @param updateCabStatusDto
     */
    public void updateCabStatusAndCity(UpdateCabStatusDto updateCabStatusDto) {
        Optional<Cab>  cab = cabRepo.findById(updateCabStatusDto.getCabId());
        if(cab.isEmpty()){
            throw new ClientErrorException("Invalid cab id");
        }
        //TODO: Validate before passing to methods
        if(updateCabStatusDto.getCabState()!=null) {
            updateCabStatus(cab.get(), CabState.valueOf(updateCabStatusDto.getCabState()));
        }
        if(updateCabStatusDto.getCity()!=null) {
            updateCabCity(cab.get(), cityRepo.findByName(updateCabStatusDto.getCity()).get());
        }
    }

    private void updateCabStatus(Cab cab, CabState state) {
        cab.setState(state);
        cabRepo.save(cab);
    }

    private void updateCabCity(Cab cab, City city) {
        cab.setCurrentCity(city);
        cabRepo.save(cab);
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

    public GetCabStatusDto getCabStatusById(long cabId) {
        Optional<Cab> cab = cabRepo.findById(cabId);
        if(cab.isEmpty()){
            throw new ClientErrorException("Invalid cabId");
        }
        GetCabStatusDto response = new GetCabStatusDto();
        response.setCabStatus(cab.get().getState().toString());
        response.setCabId(cab.get().getId());
        response.setCity(cab.get().getCurrentCity().getName());
        return  response;
    }
}
