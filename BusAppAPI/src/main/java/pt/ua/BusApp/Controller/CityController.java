package pt.ua.BusApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Service.CityService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CityController {

    private CityService cityService;
    
    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @Operation(summary = "get all cities")
    @GetMapping("/city")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities = cityService.getAllCities();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


}
