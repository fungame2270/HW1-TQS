package pt.ua.BusApp.Service;

import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.dao.requestBody.ReservationRequest;

public interface ReservationService {
    
    public Reservation getReservationById(Long Id);

    public Reservation saveReservation(ReservationRequest reservation) throws Exception;
}
