package pt.ua.BusApp.Service.Imp;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
        String uri = String.format("https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_vySWiU7PfKpdVylXUdnf4aVaKSdFEA7QdVNHpyUX&currencies=%s",currency);
        String restponse =  restTemplate.getForObject(uri, String.class);
        JSONObject jsonObject = new JSONObject(restponse);
        return jsonObject.getJSONObject("data").getDouble(currency);
    }
    
}
