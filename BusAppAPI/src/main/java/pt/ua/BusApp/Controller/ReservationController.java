package pt.ua.BusApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Service.ReservationService;
import pt.ua.BusApp.dao.requestBody.ReservationRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReservationController {
    
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @Operation(summary = "get reservation by id")
    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id){
        Reservation res = reservationService.getReservationById(id);
        if (res != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "post reservation")
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservation){
        try {
            Reservation res = reservationService.saveReservation(reservation);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
