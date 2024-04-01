package pt.ua.BusApp.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Repository.CityRepository;
import pt.ua.BusApp.Service.CityService;

@Service
public class CityServiceImp implements CityService {
    
    private CityRepository repository;

    @Autowired
    public CityServiceImp(CityRepository repository){
        this.repository = repository;
    }
    
    @Override
    public List<City> getAllCities(){
        return repository.findAll();
    }
}
