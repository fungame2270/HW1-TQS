package pt.ua.BusApp.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.CityRepository;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.Service.TripService;

@Service
public class TripServiceImp implements TripService {
    
    private TripRepository tripRepository;
    private CityRepository cityRepository;

    @Autowired
    public TripServiceImp(TripRepository repository,CityRepository cityRepository){
        this.tripRepository = repository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Trip> getTripsByDestinationAndOrigin(Long originId,Long destinationId){
        City origin = cityRepository.getReferenceById(originId);
        City destination = cityRepository.getReferenceById(destinationId);
        return tripRepository.findByOriginCityAndDestinationCity(origin,destination);
    }
}
