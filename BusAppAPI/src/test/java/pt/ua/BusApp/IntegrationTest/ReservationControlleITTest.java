package pt.ua.BusApp.IntegrationTest;

import java.time.LocalDate;
import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import pt.ua.BusApp.BusAppApplication;
import pt.ua.BusApp.Domain.Bus;
import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.BusRepository;
import pt.ua.BusApp.Repository.ReservationRepository;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.dao.requestBody.ReservationRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BusAppApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@DirtiesContext
public class ReservationControlleITTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReservationRepository repository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private BusRepository busRepository;

    @Test
    void getReservationByIdTest() throws Exception{
        Reservation res = new Reservation();
        res.setName("Tomas");
        res.setNif(133133133);

        repository.save(res);

        mvc.perform(get("/api/reservation/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Tomas")));

    }

    @Test
    void postReservation() throws Exception{
        Bus bus = new Bus("woof", 80);
        Bus savedBus = busRepository.save(bus);
        Trip trip = new Trip();
        trip.setBus(savedBus);
        trip.setDate(LocalDate.of(2003, 05, 20));
        trip.setCost(40D);
        trip.setOcupancy(0);
        Trip savedTrip = tripRepository.save(trip);

        ReservationRequest res = new ReservationRequest();
        res.setTripId(savedTrip.getId());
        res.setName("Tomas");
        res.setNif(133133133);


        mvc.perform(post("/api/reservation").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(res)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is("Tomas")));

        Optional<Reservation> saved = repository.findById(1L);

        assertThat(saved.get().getName()).isEqualTo("Tomas");

    }
}
