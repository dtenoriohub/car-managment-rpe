package br.rpe.challenger.model.repository;

import br.rpe.challenger.model.entity.CargoCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CargoCarRepositoryTest {
    @Autowired
    private CargoCarRepository cargoCarRepository;

    @Test
    void testExistsByLicencePlate() {
        CargoCar cargoCar = new CargoCar();
        cargoCar.setLicencePlate("ABC123");
        cargoCarRepository.save(cargoCar);

        // Verificando se existe
        boolean exists = cargoCarRepository.existsByLicencePlate("ABC123");

        assertTrue(exists);

    }
}