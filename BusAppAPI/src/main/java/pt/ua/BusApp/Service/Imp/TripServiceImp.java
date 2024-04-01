package pt.ua.BusApp.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.Service.TripService;

@Service
public class TripServiceImp implements TripService {
    
    private TripRepository repository;

    @Autowired
    public TripServiceImp(TripRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Trip> getAllTrips(){
        return repository.findAll();
    }
}
