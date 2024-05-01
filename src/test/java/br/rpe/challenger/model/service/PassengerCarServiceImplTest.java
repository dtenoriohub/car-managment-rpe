package br.rpe.challenger.model.service;

import br.rpe.challenger.model.entity.PassengerCar;
import br.rpe.challenger.model.repository.PassengerCarRepository;
import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;
import br.rpe.challenger.view.exception.CarAlreadyExistsException;
import br.rpe.challenger.view.exception.CarNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class PassengerCarServiceImplTest {

    @Mock
    private PassengerCarRepository repository;

    @Spy
    private PassengerCarMapper mapper;

    @InjectMocks
    @Spy
    private static PassengerCarServiceImpl service;

    private static PassengerCar entity;
    private static PassengerCarDataDTO dataDTO;
    private static PassengerCarViewDTO viewDTO;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "mapper", mapper);
    }

    @BeforeAll
    static void setUp() {
        entity = new PassengerCar(1, "ABC123", "Name", "Brand", 5);
        dataDTO = new PassengerCarDataDTO("ABC123", "Name", "Brand", 5);
        viewDTO = new PassengerCarViewDTO(1, "ABC123", "Name", "Brand", 5);
    }

    @Test
    void testShouldFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.passengerCarToPassengerCarViewDTO(anyList())).thenReturn(List.of(viewDTO));

        List<PassengerCarViewDTO> dtos = service.findAll();

        assertTrue(dtos.size() == 1);
        assertEquals(dtos.get(0).getId(), viewDTO.getId());

        verify(repository).findAll();
        verify(mapper).passengerCarToPassengerCarViewDTO(anyList());
    }

    @Test
    void testShouldFindById() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(mapper.passengerCarToPassengerCarViewDTO(any(PassengerCar.class))).thenReturn(viewDTO);

        PassengerCarViewDTO dto = assertDoesNotThrow(() -> service.findById(1));

        assertTrue(dto != null);
        assertEquals(dto, viewDTO);

        verify(repository).existsById(any());
        verify(repository).findById(any());
        verify(mapper).passengerCarToPassengerCarViewDTO(any(PassengerCar.class));
    }

    @Test
    void testShouldNotFindByIdWithInvalidId() {
        when(repository.existsById(any())).thenReturn(false);

        Throwable exception = assertThrows(CarNotFoundException.class, () -> service.findById(999));

        assertEquals("Could not find a passenger car with id = '999'", exception.getMessage());

        verify(repository).existsById(any());
        verify(repository, times(0)).findById(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void testShouldSave() {
        when(repository.existsByLicencePlate(anyString())).thenReturn(false);
        when(repository.save(any(PassengerCar.class))).thenReturn(entity);
        when(mapper.passengerCarDataDTOToPassengerCar(any())).thenReturn(entity);
        when(mapper.passengerCarToPassengerCarViewDTO(any(PassengerCar.class))).thenReturn(viewDTO);

        PassengerCarViewDTO dto = assertDoesNotThrow(() -> service.save(dataDTO));

        assertTrue(dto != null);
        assertEquals(dto, viewDTO);

        verify(repository).existsByLicencePlate(anyString());
        verify(repository).save(any(PassengerCar.class));
        verify(mapper).passengerCarDataDTOToPassengerCar(any());
        verify(mapper).passengerCarToPassengerCarViewDTO(any(PassengerCar.class));
    }

    @Test
    void testShouldNotSaveWithAlreadyExistingLicencePlate() {
        when(repository.existsByLicencePlate(anyString())).thenReturn(true);

        Throwable exception = assertThrows(CarAlreadyExistsException.class, () -> service.save(dataDTO));

        assertEquals("A passenger car with licence plate = 'ABC123' already exists", exception.getMessage());

        verify(repository).existsByLicencePlate(anyString());
        verify(repository, times(0)).save(any(PassengerCar.class));
        verifyNoInteractions(mapper);
    }

    @Test
    void testShouldUpdate() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any(PassengerCar.class))).thenReturn(entity);
        when(mapper.passengerCarDataDTOToPassengerCar(any())).thenReturn(entity);
        when(mapper.passengerCarToPassengerCarViewDTO(any(PassengerCar.class))).thenReturn(viewDTO);

        PassengerCarViewDTO dto = assertDoesNotThrow(() -> service.update(1, dataDTO));

        assertTrue(dto != null);
        assertEquals(dto, viewDTO);

        verify(repository).existsById(any());
        verify(repository).save(any(PassengerCar.class));
        verify(mapper).passengerCarDataDTOToPassengerCar(any());
        verify(mapper).passengerCarToPassengerCarViewDTO(any(PassengerCar.class));
    }

    @Test
    void testShouldNotUpdateWithInvalidId() {
        when(repository.existsById(any())).thenReturn(false);

        Throwable exception = assertThrows(CarNotFoundException.class, () -> service.update(999, dataDTO));

        assertEquals("Could not find a passenger car with id = '999'", exception.getMessage());

        verify(repository).existsById(any());
        verify(repository, times(0)).save(any(PassengerCar.class));
        verifyNoInteractions(mapper);
    }

    @Test
    void testShouldDelete() {
        when(repository.existsById(any())).thenReturn(true);
        doNothing().when(repository).deleteById(any());

        assertDoesNotThrow(() -> service.delete(1));

        verify(repository).existsById(any());
        verify(repository).deleteById(any());
    }

    @Test
    void testShouldNotDeleteWithInvalidId() {
        when(repository.existsById(any())).thenReturn(false);

        Throwable exception = assertThrows(CarNotFoundException.class, () -> service.delete(999));

        assertEquals("Could not find a passenger car with id = '999'", exception.getMessage());

        verify(repository).existsById(any());
        verify(repository, times(0)).deleteById(any());
    }

}