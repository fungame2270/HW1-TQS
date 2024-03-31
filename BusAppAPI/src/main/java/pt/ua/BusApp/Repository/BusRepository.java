package pt.ua.BusApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ua.BusApp.Domain.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    
}
