package br.rpe.challenger.view.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoCarDataDTO {

    @NotBlank(message = "Licence Plate should not be blank!")
    private String licencePlate;

    @NotBlank(message = "Name should not be blank!")
    private String name;

    @NotBlank(message = "Brand should not be blank!")
    private String brand;

    @Positive(message = "Capacity should not be blank")
    private int capacity;

    @Positive(message = "QuantityCarBody should not be blank")
    private int quantityCarBody;
}
