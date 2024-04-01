package pt.ua.BusApp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Repository.CityRepository;
import pt.ua.BusApp.Service.Imp.CityServiceImp;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {
    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityServiceImp service;

    @Test
    void getAllCitiesTest(){
        City city = new City("Lisbon");
        City city2 = new City("Porto");
        List<City> cities = Arrays.asList(city,city2);

        when(repository.findAll()).thenReturn(cities);

        List<City> list = service.getAllCities();
        assertThat(list).isEqualTo(cities);
    }
    
}
