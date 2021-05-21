package com.cabs.app.repo;

import com.cabs.app.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabRepo extends JpaRepository<Cab, Long> {
    Optional<Cab> findById(long id);
}
