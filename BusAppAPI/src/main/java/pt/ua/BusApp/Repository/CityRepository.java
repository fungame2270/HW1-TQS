package pt.ua.BusApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.ua.BusApp.Domain.City;

public interface CityRepository extends JpaRepository<City,Long> {
    
}
