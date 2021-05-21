package com.cabs.app.repo;

import com.cabs.app.model.Cab;
import com.cabs.app.state.CabState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CabRepo extends JpaRepository<Cab, Long> {
    Optional<Cab> findById(long id);
    List<Cab> findByStateAndCurrentCityNameOrderByLastOnTrip(CabState cabState, String currentCityName);
}
