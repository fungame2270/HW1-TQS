package pt.ua.BusApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Service.TripService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TripController {

    TripService tripService;

    @Autowired
    public TripController(TripService tripService){
        this.tripService = tripService;
    }
    
    @Operation(summary = "get all trips by origin and destination and currency")
    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getAllTrips(@RequestParam Long originCityId,@RequestParam Long destinationCityId,@RequestParam(required = false) String currency){
        List<Trip> trips;
        if(currency == null){
            trips = tripService.getTripsByDestinationAndOrigin(originCityId,destinationCityId);
        }else{
            trips = tripService.getTripsByDestinationAndOriginCurrencyChange(originCityId,destinationCityId,currency);
        }

        return new ResponseEntity<>(trips,HttpStatus.OK);
    }
}
