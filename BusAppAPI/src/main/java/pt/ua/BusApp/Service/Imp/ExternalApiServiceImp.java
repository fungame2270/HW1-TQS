package pt.ua.BusApp.Service.Imp;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import pt.ua.BusApp.Service.ExternalApiService;

@Service
public class ExternalApiServiceImp implements ExternalApiService {

    private RestTemplate restTemplate;

    public ExternalApiServiceImp(){
        this.restTemplate = new RestTemplate();
    }

    @Cacheable("currency")
    @Override
    public Double getConvertRation(String currency){
        List<String> currencys = Arrays.asList("USD","EUR","CAD","JPY");
        if (currencys.contains(currency)){
            String uri = String.format("https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_vySWiU7PfKpdVylXUdnf4aVaKSdFEA7QdVNHpyUX&currencies=%s",currency);
            String restponse =  restTemplate.getForObject(uri, String.class);
            JSONObject jsonObject = new JSONObject(restponse);
            return jsonObject.getJSONObject("data").getDouble(currency);
        }
        return -1.0;
    }
    
}
