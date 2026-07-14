package com.semicore.booking.dao.entities;
//dao == modal == persistence

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class FlightForSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_num")
    private String flightNumber;

    @Column(name = "departure")
    private String departureAirport;

    @Column(name = "destination")
    private String destinationAirport;

}
