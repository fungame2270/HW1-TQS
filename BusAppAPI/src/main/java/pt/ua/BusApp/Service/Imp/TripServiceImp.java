package pt.ua.BusApp.Service.Imp;

import java.util.List;

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
        City origin = cityRepository.findById(originId).get();
        City destination = cityRepository.findById(destinationId).get();
        return tripRepository.findByOriginCityAndDestinationCity(origin,destination);
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
