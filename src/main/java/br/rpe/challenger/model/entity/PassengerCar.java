package br.rpe.challenger.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PASSENGER_CAR")
public class PassengerCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LICENCE_PLATE",unique = true)
    private String licencePlate;

    @Column(name = "NAME")
    private String name;

    @Column(name= "BRAND")
    private String brand;

    @Column(name= "NUMBER_OF_PASSENGERS")
    private int numberOfPassengers;
}
