package pt.ua.BusApp.Service.Imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.BusApp.Domain.Reservation;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.ReservationRepository;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.Service.ReservationService;
import pt.ua.BusApp.dao.requestBody.ReservationRequest;

@Service
public class ReservationServiceImp implements ReservationService{

    private ReservationRepository reservationRepository;
    private TripRepository tripRepository;

    @Autowired
    public ReservationServiceImp(ReservationRepository reservationRepository,TripRepository tripRepository){
        this.reservationRepository = reservationRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public Reservation getReservationById(Long id){
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation saveReservation(ReservationRequest reservationRequest) throws Exception{

        Optional<Trip> optional = tripRepository.findById(reservationRequest.getTripId());
        Trip trip = optional.get();
        if(trip.getBus().getCapacity() <= trip.getOcupancy()){
            throw new Exception("There is no available Space");
        }

        Reservation reservation = new Reservation();
        reservation.setName(reservationRequest.getName());
        reservation.setMorada(reservationRequest.getMorada());
        reservation.setCardNumber(reservationRequest.getCardNumber());
        reservation.setCardType(reservationRequest.getCardType());
        reservation.setTrip(trip);
        reservation.setNif(reservationRequest.getNif());
        trip.setOcupancy(trip.getOcupancy() + 1);
        return reservationRepository.save(reservation);
    }
    
}
