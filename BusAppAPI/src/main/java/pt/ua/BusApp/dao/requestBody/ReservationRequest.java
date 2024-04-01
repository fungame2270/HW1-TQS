package pt.ua.BusApp.dao.requestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    
    private Long tripId;

    private String name;

    private String morada;

    private int nif;

    private String cardType;

    private int cardNumber;
}
