package pt.ua.BusApp.Service;

import pt.ua.BusApp.Domain.Reservation;

public interface ReservationService {
    
    public Reservation getReservationById(Long Id);

    public Reservation saveReservation(Reservation reservation);
}
