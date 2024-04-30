package br.rpe.challenger.view.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CargoCarDataDTOTest {
   private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    @Test
    void shouldLicencePlateNotBlank() {
        CargoCarDataDTO cargo = new CargoCarDataDTO("", "Name", "Brand", 100, 2);
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(cargo);
        assertFalse(violations.isEmpty());
    }
    @Test
    void shouldNameNotBlank() {
        CargoCarDataDTO cargo = new CargoCarDataDTO("ABC123", "", "Brand", 100, 2);
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(cargo);
        assertFalse(violations.isEmpty());
    }
    @Test
    void shouldBrandNotBlank() {
        CargoCarDataDTO cargo = new CargoCarDataDTO("ABC123", "Name", "", 100, 2);
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(cargo);
        assertFalse(violations.isEmpty());
    }
    @Test
    void shouldCapacityNotPositive() {
        CargoCarDataDTO cargo = new CargoCarDataDTO("ABC123", "Name", "Brand", 0, 2);
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(cargo);
        assertFalse(violations.isEmpty());
    }
    @Test
    void shouldQuantityCarBodyPositive() {
        CargoCarDataDTO cargo = new CargoCarDataDTO("ABC123", "Name", "Brand", 100, -2);
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(cargo);
        assertFalse(violations.isEmpty());
    }

}