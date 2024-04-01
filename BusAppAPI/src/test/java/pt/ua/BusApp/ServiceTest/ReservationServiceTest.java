package pt.ua.BusApp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.BusApp.Domain.Bus;
import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.ReservationRepository;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.Service.Imp.ReservationServiceImp;
import pt.ua.BusApp.dao.requestBody.ReservationRequest;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private TripRepository tripRepository;
    @Mock
    private ReservationRepository reservationRepository; 

    @InjectMocks
    private ReservationServiceImp service;

    @Test
    public void getReservationByIdTest(){
        Reservation res = new Reservation();
        res.setId(1L);
        res.setMorada("rua joao moni");
        res.setName("tomas");
        when(reservationRepository.findById((long)1)).thenReturn(Optional.of(res));

        Reservation optional = service.getReservationById(1L);

        assertThat(optional).isEqualTo(res);
    }

    @Test
    public void saveReservationTest() throws Exception{
        Trip trip = new Trip();
        trip.setId(1L);
        trip.setOcupancy(10);
        Bus bus = new Bus("Alfa", 20);
        trip.setBus(bus);
        Reservation reservation = new Reservation(2L, trip, "Tomas", "rua Damasio", 133133133, "Debit", 999999999);

        when(tripRepository.findById(1L)).thenReturn(Optional.of(trip));
        when(reservationRepository.save(Mockito.any())).thenReturn(reservation);

        ReservationRequest request = new ReservationRequest(1L, "Tomas", "rua Damasio", 133133133, "Debit", 999999999);
        Reservation res = service.saveReservation(request);
        assertThat(res.getName()).isEqualTo(request.getName());
        assertThat(res.getMorada()).isEqualTo(request.getMorada());
        assertThat(res.getNif()).isEqualTo(request.getNif());
        assertThat(res.getCardType()).isEqualTo(request.getCardType());
        assertThat(res.getCardNumber()).isEqualTo(request.getCardNumber());
        assertThat(res.getTrip().getId()).isEqualTo(request.getTripId());
    }
}
