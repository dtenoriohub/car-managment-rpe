package br.rpe.challenger.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    @NotBlank(message = "Licence Plate should not be blank!")
    private String licencePlate;

    @Column(name = "NAME")
    @NotBlank(message = "Name should not be blank!")
    private String name;

    @Column(name= "BRAND")
    @NotBlank(message = "Brand should not be blank!")
    private String brand;

    @Column(name= "NUMBER_OF_PASSENGERS")
    @Positive(message = "Number of passengers should be a positive value")
    private int numberOfPassengers;
}
