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

import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.Service.Imp.TripServiceImp;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {
    @Mock
    private TripRepository repository;

    @InjectMocks
    private TripServiceImp service;

    @Test
    void getAllTrips(){
        Trip trip1 = new Trip();
        trip1.setDate(LocalDate.of(2003, 10, 5));
        Trip trip2 = new Trip();
        trip2.setDate(LocalDate.of(2004, 10, 5));

        List<Trip> trips = Arrays.asList(trip1,trip2);

        when(repository.findAll()).thenReturn(trips);

        List<Trip> list = service.getAllTrips();


        assertThat(list).isEqualTo(trips);
    }
    
}
