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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import pt.ua.BusApp.BusAppApplication;
import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Repository.CityRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BusAppApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@DirtiesContext
public class CityControllerITTest {
    
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    void whenGetAllCities_ReceiveAllCities() throws Exception {
        City city1 = new City();
        city1.setName("Lisbon");
        City city2 = new City();
        city1.setName("Porto");
        cityRepository.save(city1);
        cityRepository.save(city2);
        cityRepository.flush();

        mvc.perform(
            get("/api/city")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].name", is(city1.getName())))
            .andExpect(jsonPath("$[1].name", is(city2.getName()))).andReturn();
    }
}
