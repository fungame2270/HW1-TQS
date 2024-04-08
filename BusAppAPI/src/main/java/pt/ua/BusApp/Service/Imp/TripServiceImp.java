package pt.ua.BusApp.Service.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.CityRepository;
import pt.ua.BusApp.Repository.TripRepository;
import pt.ua.BusApp.Service.CurrencyService;
import pt.ua.BusApp.Service.TripService;

@Service
public class TripServiceImp implements TripService {
    
    private TripRepository tripRepository;
    private CityRepository cityRepository;
    private CurrencyService currencyService;

    @Autowired
    public TripServiceImp(TripRepository repository,CityRepository cityRepository,CurrencyService currencyService){
        this.tripRepository = repository;
        this.cityRepository = cityRepository;
        this.currencyService = currencyService;
    }

    @Override
    public List<Trip> getTripsByDestinationAndOrigin(Long originId,Long destinationId){
        Optional<City> origin = cityRepository.findById(originId);
        Optional<City> destination = cityRepository.findById(destinationId);
        if (origin.isPresent() && destination.isPresent()){
            return tripRepository.findByOriginCityAndDestinationCity(origin.get(),destination.get());
        }else{
            return new ArrayList<Trip>();
        }
    }

    public List<Trip> getTripsByDestinationAndOriginCurrencyChange(Long originId,Long destinationId,String currecy){
        City origin = cityRepository.findById(originId).get();
        City destination = cityRepository.findById(destinationId).get();
        List<Trip> trips = tripRepository.findByOriginCityAndDestinationCity(origin,destination);
        for (Trip trip : trips) {
            trip.setCost(currencyService.convertCurrency(trip.getCost(), currecy));
        }
        return trips;
    }
}
