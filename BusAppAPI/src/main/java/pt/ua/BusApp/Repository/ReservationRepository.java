package pt.ua.BusApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.ua.BusApp.Domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{
    
}
