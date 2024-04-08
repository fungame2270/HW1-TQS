package pt.ua.BusApp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.BusApp.Service.ExternalApiService;
import pt.ua.BusApp.Service.Imp.CurrencyServiceImp;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {
    @Mock
    private ExternalApiService externalApiServiceMock;
    
    @InjectMocks
    private CurrencyServiceImp currencyService;

    @Test
    void convertCurrency(){
        String currency = "EUR";
        Double price = 40D;
        Double ration = 1.2;
        when(externalApiServiceMock.getConvertRation(currency)).thenReturn(ration);

        assertThat(currencyService.convertCurrency(price, currency)).isEqualTo(price*ration);
    }
}
