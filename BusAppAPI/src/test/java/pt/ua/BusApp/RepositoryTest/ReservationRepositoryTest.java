package pt.ua.BusApp.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Repository.ReservationRepository;

@DataJpaTest
public class ReservationRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void whenSaveCityGetCityById(){
        Reservation reservation = new Reservation();
        reservation.setName("Tomás");

        reservationRepository.save(reservation);

        Optional<Reservation> entity = reservationRepository.findById(reservation.getId());

        assertThat(entity.get().getName()).isEqualTo(reservation.getName());
    }

    @Test
    void whenDeleteCityNotFound(){
        Reservation reservation = new Reservation();
        reservation.setName("Tomás");

        entityManager.persistAndFlush(reservation);
        reservationRepository.delete(reservation);
        Optional<Reservation> entity = reservationRepository.findById(reservation.getId());

        assertTrue(entity.isEmpty());
    }

    @Test
    void findAllReturnAll(){
        Reservation reservation = new Reservation();
        reservation.setName("Tomás");
        Reservation reservation2 = new Reservation();
        reservation2.setName("Victal");
        entityManager.persist(reservation);
        entityManager.persist(reservation2);
        entityManager.flush();

        List<Reservation> allCities = reservationRepository.findAll();

        assertThat(allCities).hasSize(2).extracting(Reservation::getName).containsOnly(reservation.getName(),reservation2.getName());
    }
}
