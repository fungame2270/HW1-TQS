package pt.ua.BusApp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.CityRepository;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.Service.Imp.TripServiceImp;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {
    @Mock
    private TripRepository tripRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private TripServiceImp service;

    @Test
    void getAllTripsByoriginDestinatio(){
        City lisbon = new City(1L,"lisbon");
        City porto = new City(2L,"Porto");
        Trip trip1 = new Trip();
        trip1.setDate(LocalDate.of(2003, 10, 5));
        trip1.setOriginCity(porto);
        trip1.setDestinationCity(lisbon);
        Trip trip2 = new Trip();
        trip2.setDate(LocalDate.of(2004, 10, 5));
        trip2.setOriginCity(lisbon);
        trip2.setDestinationCity(porto);

        List<Trip> trips = Arrays.asList(trip1);

        when(cityRepository.getReferenceById(1L)).thenReturn(lisbon);
        when(cityRepository.getReferenceById(2L)).thenReturn(porto);
        when(tripRepository.findByOriginCityAndDestinationCity(porto, lisbon)).thenReturn(trips);
        
        List<Trip> list = service.getTripsByDestinationAndOrigin(porto.getId(),lisbon.getId());


        assertThat(list).isEqualTo(trips);
    }
    
}
