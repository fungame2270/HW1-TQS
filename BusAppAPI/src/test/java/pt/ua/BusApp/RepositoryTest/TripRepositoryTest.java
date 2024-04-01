package pt.ua.BusApp.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.BusApp.Domain.Bus;
import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.TripRepository;

@DataJpaTest
public class TripRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TripRepository tripRepository;

    @Test
    public void whenSaveTripGetTripById(){
        Trip trip = new Trip();
        LocalDate date =  LocalDate.of(2000, 5, 20);
        trip.setDate(date);

        tripRepository.save(trip);

        Optional<Trip> entity = tripRepository.findById(trip.getId());

        assertThat(entity.get().getDate()).isEqualTo(trip.getDate());
    }

    @Test
    public void whenDeleteTripNotFound(){
        Trip trip = new Trip();
        LocalDate date =  LocalDate.of(2000, 5, 20);
        trip.setDate(date);

        entityManager.persistAndFlush(trip);
        tripRepository.delete(trip);
        Optional<Trip> entity = tripRepository.findById(trip.getId());

        assertTrue(entity.isEmpty());
    }

    @Test
    public void findAllReturnAll(){
        Trip trip = new Trip();
        LocalDate date =  LocalDate.of(2000, 5, 20);
        trip.setDate(date);
        Trip trip2 = new Trip();
        LocalDate date2 =  LocalDate.of(2000, 5, 20);
        trip2.setDate(date2);
        entityManager.persist(trip);
        entityManager.persist(trip2);
        entityManager.flush();

        List<Trip> allCities = tripRepository.findAll();

        assertThat(allCities).hasSize(2).extracting(Trip::getDate).containsOnly(trip.getDate(),trip.getDate());
    }

    @Test
    public void getTripsByDestinationAndOrigin(){
        City lisbon = new City("libons");
        City porto = new City("Porto");
        City aveiro = new City("Aveiro");

        Trip lisbonPorto = new Trip();
        lisbonPorto.setOriginCity(lisbon);
        lisbonPorto.setDestinationCity(porto);
        Trip aveiroPorto = new Trip();
        aveiroPorto.setOriginCity(aveiro);
        aveiroPorto.setDestinationCity(porto);

        entityManager.persist(lisbonPorto);
        entityManager.persist(aveiroPorto);
        entityManager.flush();

        List<Trip> list = tripRepository.findByOriginCityAndDestinationCity(aveiro, porto);

        assertThat(list).hasSize(1).extracting(Trip::getOriginCity).containsOnly(aveiroPorto.getOriginCity());

    }
}
