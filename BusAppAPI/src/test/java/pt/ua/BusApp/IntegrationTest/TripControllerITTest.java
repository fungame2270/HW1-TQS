package pt.ua.BusApp.IntegrationTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import pt.ua.BusApp.BusAppApplication;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BusAppApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class TripControllerITTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    void getAllTrips() throws Exception{

        mvc.perform(get("/api/trips").param("originCityId", "1").param("destinationCityId", "2").param("currency", "EUR")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(2)));
    }

}
