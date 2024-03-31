package pt.ua.BusApp.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Repository.CityRepository;

@DataJpaTest
public class CityRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void whenSaveCityGetCityById(){
        City city = new City("Lisbon");

        cityRepository.save(city);

        Optional<City> entity = cityRepository.findById(city.getId());

        assertThat(entity.get().getName()).isEqualTo(city.getName());
    }

    @Test
    public void whenDeleteCityNotFound(){
        City city = new City("Lisbon");

        entityManager.persistAndFlush(city);
        cityRepository.delete(city);
        Optional<City> entity = cityRepository.findById(city.getId());

        assertTrue(entity.isEmpty());
    }

    @Test
    public void findAllReturnAll(){
        City city = new City("Lisbon");
        City city2 = new City("Porto");
        entityManager.persist(city);
        entityManager.persist(city2);
        entityManager.flush();

        List<City> allCities = cityRepository.findAll();

        assertThat(allCities).hasSize(2).extracting(City::getName).containsOnly(city.getName(),city2.getName());
    }
}
