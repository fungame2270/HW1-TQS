package pt.ua.BusApp.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.BusApp.Service.CurrencyService;
import pt.ua.BusApp.Service.ExternalApiService;

@Service
public class CurrencyServiceImp implements CurrencyService {
    
    private ExternalApiService externalApi;

    @Autowired
    public CurrencyServiceImp(ExternalApiService externalApiService){
        this.externalApi = externalApiService;
    }
    
    public Double convertCurrency(Double value,String currency){
        Double ratio = externalApi.getConvertRation(currency);
        return value*ratio;
    }
}



