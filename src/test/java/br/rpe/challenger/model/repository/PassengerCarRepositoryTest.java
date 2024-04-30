package br.rpe.challenger.model.repository;

import br.rpe.challenger.model.entity.PassengerCar;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PassengerCarRepositoryTest {
    @Autowired
    private PassengerCarRepository repository;

    @Mock
    PassengerCar passengerCar;
    @Test
    void existsByLicencePlate() {
        passengerCar = new PassengerCar();
        passengerCar.setLicencePlate("ABC123");
        repository.save(passengerCar);
        boolean exists = repository.existsByLicencePlate("ABC123");
    }
}