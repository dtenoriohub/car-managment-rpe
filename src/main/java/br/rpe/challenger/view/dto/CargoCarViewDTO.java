package br.rpe.challenger.view.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoCarViewDTO {

    private Integer id;

    private String licencePlate;

    private String name;

    private String brand;

    private int capacity;

    private int quantityCarBody;

}
