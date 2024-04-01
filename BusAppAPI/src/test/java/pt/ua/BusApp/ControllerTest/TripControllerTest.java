package pt.ua.BusApp.ControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import pt.ua.BusApp.Controller.TripController;
import pt.ua.BusApp.Domain.Bus;
import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Service.TripService;

@WebMvcTest(TripController.class)
public class TripControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TripService tripService;

    @Test
    void getAllTrips() throws Exception{
        Bus bus1 = new Bus("Avolta", 80);
        bus1.setId(2L);
        List<Trip> trips = new ArrayList<>();
        Trip trip1 = new Trip();
        trip1.setId(1L);
        trip1.setReservations(new ArrayList<Reservation>());
        trip1.setBus(bus1);
        trip1.setDate(LocalDate.of(2024, 1, 1));
        trips.add(trip1);

        Trip trip2 = new Trip();
        trip2.setId(2L);
        trip2.setReservations(new ArrayList<Reservation>());
        trip2.setBus(bus1);
        trip2.setDate(LocalDate.of(2024, 1, 1));
        trips.add(trip2);

        when(tripService.getTripsByDestinationAndOrigin(1L, 2L)).thenReturn(trips);

        mvc.perform(get("/api/trips").param("originCityId", "1").param("destinationCityId", "2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[0].reservations").doesNotExist());
    }
}
