package br.rpe.challenger.model.entity;
import jakarta.persistence.*;
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
    private String licencePlate;

    @Column(name= "NAME")
    private String name;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "CAPACITY")
    private int capacity;

    @Column(name = "QUANTITY_CAR_BODY")
    private int quantityCarBody;
}
