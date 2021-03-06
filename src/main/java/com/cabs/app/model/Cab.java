package com.cabs.app.model;

import com.cabs.app.state.CabState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cab {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    @NotBlank
    String licenseNumber;

    @Enumerated(EnumType.STRING)
    CabState state;

    Instant lastOnTrip;

    @ManyToOne
    @NotNull
    City currentCity;

    @OneToMany(mappedBy = "cab")
    List<Booking> bookingHistory;


}
