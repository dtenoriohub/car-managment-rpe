package br.rpe.challenger.model.repository;

import br.rpe.challenger.model.entity.PassengerCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerCarRepository extends JpaRepository<PassengerCar,Integer> {

    public boolean existsByLicencePlate(String licencePlate);
}
