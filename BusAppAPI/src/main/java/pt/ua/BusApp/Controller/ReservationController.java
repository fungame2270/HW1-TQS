package pt.ua.BusApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {
    
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id){
        Reservation res = reservationService.getReservationById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        Reservation res = reservationService.saveReservation(reservation);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
