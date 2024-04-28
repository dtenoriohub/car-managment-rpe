package br.rpe.challenger.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CARGO_CAR")
public class CargoCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "LICENCE_PLATE",unique = true)
    @NotBlank(message = "Licence Plate should not be blank!")
    private String licencePlate;

    @Column(name= "NAME")
    @NotBlank(message = "Name should not be blank!")
    private String name;

    @Column(name = "BRAND")
    @NotBlank(message = "Brand should not be blank!")
    private String brand;

    @Column(name = "CAPACITY")
    @NotBlank(message = "Capacity should not be blank")
    private int capacity;

    @Column(name = "QUANTITY_CAR_BODY")
    @NotBlank(message = "QuantityCarBody should not be blank")
    private int quantityCarBody;
}
