package pt.ua.BusApp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;

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
    private CityRepository carRepository;

    @InjectMocks
    private CityServiceImp carService;

    @Test
    void getAllCitiesTest(){
        City city = new City("Tomas");

        assertThat(city).isEqualTo(city);
    }
    
}
