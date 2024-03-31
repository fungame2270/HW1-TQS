package pt.ua.BusApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {

    private CityService cityService;
    
    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/city")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities = cityService.getAllCities();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


}
