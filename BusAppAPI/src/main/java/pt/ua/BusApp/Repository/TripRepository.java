package pt.ua.BusApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Domain.Trip;

public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByOriginCityAndDestinationCity(City originCity,City destinationCity);
}
