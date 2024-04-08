package pt.ua.BusApp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import pt.ua.BusApp.Service.Imp.ExternalApiServiceImp;

@TestInstance(Lifecycle.PER_CLASS)
public class ExternalApiServiceTest {
    
    private ExternalApiServiceImp exteranalApi;

    @BeforeAll
    public void setup(){
        this.exteranalApi = new ExternalApiServiceImp();
    }

    @Test
    void testApi(){
        Double value = exteranalApi.getConvertRation("EUR");

        assertThat(value).isGreaterThan(0);
    }
}
