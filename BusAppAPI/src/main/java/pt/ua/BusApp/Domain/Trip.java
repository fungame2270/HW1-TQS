package pt.ua.BusApp.Domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

     @ManyToOne
     @JoinColumn(name = "bus_id")
     private Bus bus;

     @Column
     private Double cost;

     @Column
     private int ocupancy;

     @JsonIgnore
     @OneToMany
     private List<Reservation> reservations;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "originCity")
     private City originCity;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "destinationCity")
     private City destinationCity;
}
