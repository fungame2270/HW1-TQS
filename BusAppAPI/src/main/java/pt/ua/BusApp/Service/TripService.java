package pt.ua.BusApp.Service;

import java.util.List;

import pt.ua.BusApp.Domain.Trip;

public interface TripService {
    
    public List<Trip> getTripsByDestinationAndOrigin(Long originId,Long destinationId);
}
