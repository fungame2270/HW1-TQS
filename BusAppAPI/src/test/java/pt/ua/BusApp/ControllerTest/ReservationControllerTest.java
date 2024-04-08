package pt.ua.BusApp.ControllerTest;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import pt.ua.BusApp.Controller.ReservationController;
import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Service.ReservationService;

@WebMvcTest(ReservationController.class)
@ActiveProfiles("test")
public class ReservationControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    void getReservationByIdTest() throws Exception{
        Reservation res = new Reservation();
        res.setId(1L);
        res.setName("Tomas");
        res.setNif(133133133);

        when(reservationService.getReservationById(1L)).thenReturn(res);

        mvc.perform(get("/api/reservation/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Tomas")));

        verify(reservationService,times(1)).getReservationById(1L);
    }

    @Test
    void postReservation() throws Exception{
        Reservation res = new Reservation();
        res.setId(1L);
        res.setName("Tomas");
        res.setNif(133133133);

        when(reservationService.saveReservation(Mockito.any())).thenReturn(res);

        mvc.perform(post("/api/reservation").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(res)))
            .andExpect(jsonPath("$.name", is("Tomas")));

        verify(reservationService,times(1)).saveReservation(Mockito.any());

    }
}
