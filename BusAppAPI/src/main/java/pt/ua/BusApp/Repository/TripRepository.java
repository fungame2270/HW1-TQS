package pt.ua.BusApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.ua.BusApp.Domain.Trip;

public interface TripRepository extends JpaRepository<Trip,Long> {
    
}
