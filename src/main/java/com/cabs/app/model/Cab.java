package com.cabs.app.model;

import com.cabs.app.state.CabState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cab {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    CabState state;

    Instant lastOnTrip;

    @ManyToOne
    City currentCity;


}
