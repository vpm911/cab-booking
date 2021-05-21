package com.cabs.app.repo;

import com.cabs.app.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepo extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}
