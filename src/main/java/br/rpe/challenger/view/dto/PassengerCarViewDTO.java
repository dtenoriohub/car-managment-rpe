package br.rpe.challenger.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerCarViewDTO {

    private Integer id;

    private String licencePlate;

    private String name;

    private String brand;

    private int numberOfPassengers;
}
