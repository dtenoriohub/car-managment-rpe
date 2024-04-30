package br.rpe.challenger.view.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PassengerCarDataDTOTest {
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    @Test
    void licencePlateNotBlank() {
        PassengerCarDataDTO car = new PassengerCarDataDTO("", "Name", "Brand", 4);
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(car);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nameNotBlank() {
        PassengerCarDataDTO car = new PassengerCarDataDTO("ABC123", "", "Brand", 4);
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(car);
        assertFalse(violations.isEmpty());
    }

    @Test
    void brandNotBlank() {
        PassengerCarDataDTO car = new PassengerCarDataDTO("ABC123", "Name", "", 4);
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(car);
        assertFalse(violations.isEmpty());
    }

    @Test
    void numberOfPassengersPositive() {
        PassengerCarDataDTO car = new PassengerCarDataDTO("ABC123", "Name", "Brand", -4);
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(car);
        assertFalse(violations.isEmpty());
    }


}