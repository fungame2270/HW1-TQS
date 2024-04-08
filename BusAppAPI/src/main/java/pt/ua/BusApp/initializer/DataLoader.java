package pt.ua.BusApp.initializer;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import pt.ua.BusApp.Domain.Bus;
import pt.ua.BusApp.Domain.City;
import pt.ua.BusApp.Domain.Trip;
import pt.ua.BusApp.Repository.BusRepository;
import pt.ua.BusApp.Repository.CityRepository;
import pt.ua.BusApp.Repository.TripRepository;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    private BusRepository busRepository;
    private TripRepository tripRepository;
    private CityRepository cityRepository;

    @Autowired
    public DataLoader(BusRepository br,TripRepository tr,CityRepository cr){
        this.busRepository = br;
        this.tripRepository = tr;
        this.cityRepository = cr;
    }
    
    public void run(String... args) throws Exception{
        if (!cityRepository.findAll().isEmpty()){
            return;
        }
        City lisbon = new City("Lisbon");
        City porto = new City("Porto");
        City santarem = new City("Santarém");

        Bus b123 = new Bus("b123", 80);
        Bus b231 = new Bus("b231",80);
        Bus b567 = new Bus("b567",70);
        busRepository.saveAll(Arrays.asList(b123,b231,b567));


        Trip lisbonPorto = new Trip();
        lisbonPorto.setBus(b567);
        lisbonPorto.setCost(40D);
        lisbonPorto.setOriginCity(lisbon);
        lisbonPorto.setDestinationCity(porto);
        lisbonPorto.setDate(LocalDate.of(2024,4,12));

        Trip portoLisbon = new Trip();
        portoLisbon.setBus(b123);
        portoLisbon.setCost(40D);
        portoLisbon.setOriginCity(porto);
        portoLisbon.setDestinationCity(lisbon);
        portoLisbon.setDate(LocalDate.of(2024,4,13));

        Trip portoSantarem = new Trip();
        portoSantarem.setBus(b231);
        portoSantarem.setCost(50D);
        portoSantarem.setOriginCity(porto);
        portoSantarem.setDestinationCity(santarem);
        portoSantarem.setDate(LocalDate.of(2024,4,13));

        tripRepository.saveAll(Arrays.asList(lisbonPorto,portoLisbon,portoSantarem));
    }
}
