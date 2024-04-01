package pt.ua.BusApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Service.TripService;

@RestController
@RequestMapping("/api")
public class TripController {

    TripService tripService;

    @Autowired
    public TripController(TripService tripService){
        this.tripService = tripService;
    }
    
    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getAllTrips(@RequestParam Long originCityId,@RequestParam Long destinationCityId){
        
        List<Trip> trips = tripService.getTripsByDestinationAndOrigin(originCityId,destinationCityId);

        return new ResponseEntity<>(trips,HttpStatus.OK);
    }
}
