package pt.ua.BusApp.Domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Trip")
public class Trip {
    
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Temporal(TemporalType.DATE)
     private LocalDate date;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "bus_id")
     private Bus bus;

     @JsonIgnore
     @OneToMany(cascade = CascadeType.ALL)
     private List<Reservation> reservations;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "originCity_id")
     private City originCity;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "destinationCity_id")
     private City destinationCity;
}
