package br.rpe.challenger.view.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerCarDataDTO {

    @NotBlank(message = "Licence Plate should not be blank!")
    private String licencePlate;

    @NotBlank(message = "Name should not be blank!")
    private String name;

    @NotBlank(message = "Brand should not be blank!")
    private String brand;

    @Positive(message = "Number of passengers should be a positive value")
    private int numberOfPassengers;
}


