package br.rpe.challenger.view.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PassengerCarDataDTOTest {
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();
    private PassengerCarDataDTO dto;

    @BeforeEach
    void setUp() {
        dto = new PassengerCarDataDTO("ABC123", "Name", "Brand", 4);
    }

    @Test
    void testDtoValid() {
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(dto);
        assertEquals(0, violations.size());
    }

    @Test
    void testLicencePlateNotValid() {
        dto.setLicencePlate("  ");
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Licence Plate should not be blank!", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void testNameNotValid() {
        dto.setName("  ");
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Name should not be blank!", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void testBrandNotValid() {
        dto.setBrand("  ");
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Brand should not be blank!", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void testNumberOfPassengersNotValid() {
        dto.setNumberOfPassengers(-5);
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Number of passengers should be a positive value", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void testDtoInvalid() {
        PassengerCarDataDTO invalidDto = new PassengerCarDataDTO();
        Set<ConstraintViolation<PassengerCarDataDTO>> violations = validator.validate(invalidDto);

        assertAll(
                () -> assertEquals(4, violations.size()),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Licence Plate should not be blank!"))),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Name should not be blank!"))),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Brand should not be blank!"))),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Number of passengers should be a positive value")))
        );
    }

}