package br.rpe.challenger.view.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CargoCarDataDTOTest {
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();
    private CargoCarDataDTO dto;

    @BeforeEach
    void setUp() {
        dto = new CargoCarDataDTO("ABC123", "Name", "Brand", 50, 50);
    }

    @Test
    void testDtoValid() {
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(dto);
        assertEquals(0, violations.size());
    }

    @Test
    void testLicencePlateNotValid() {
        dto.setLicencePlate("  ");
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Licence Plate should not be blank!", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void testNameNotValid() {
        dto.setName("  ");
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Name should not be blank!", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void testBrandNotValid() {
        dto.setBrand("  ");
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Brand should not be blank!", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void shouldCapacityNotPositive() {
        dto.setCapacity(-100);
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("Capacity should be a positive value!", violations.stream().findFirst().get().getMessage());
    }
    @Test
    void shouldQuantityCarBodyPositive() {
        dto.setQuantityCarBody(-10);
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertEquals("QuantityCarBody should be a positive value!", violations.stream().findFirst().get().getMessage());
    }

    @Test
    void testDtoInvalid() {
        CargoCarDataDTO invalidDto = new CargoCarDataDTO();
        Set<ConstraintViolation<CargoCarDataDTO>> violations = validator.validate(invalidDto);

        assertAll(
                () -> assertEquals(5, violations.size()),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Licence Plate should not be blank!"))),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Name should not be blank!"))),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Brand should not be blank!"))),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Capacity should be a positive value!"))),
                () -> assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("QuantityCarBody should be a positive value!")))
        );
    }
}