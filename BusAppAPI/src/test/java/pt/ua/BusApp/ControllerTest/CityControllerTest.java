package pt.ua.BusApp.ControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import pt.ua.BusApp.Controller.CityController;
import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Service.CityService;

@WebMvcTest(CityController.class)
@ActiveProfiles("test")
public class CityControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    @Test
    void whenGetAllCities_ReceiveAllCities() throws Exception {
        List<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.setId(1L);
        city1.setName("Lisbon");
        City city2 = new City();
        city1.setId(2L);
        city1.setName("Porto");
        cities.add(city1);
        cities.add(city2);
        when(cityService.getAllCities()).thenReturn(cities);

        mvc.perform(
            get("/api/city")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].name", is(city1.getName())))
            .andExpect(jsonPath("$[1].name", is(city2.getName())));
    }
}
