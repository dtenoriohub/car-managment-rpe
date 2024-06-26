package br.rpe.challenger.model.service;

import br.rpe.challenger.model.entity.CargoCar;
import br.rpe.challenger.model.repository.CargoCarRepository;
import br.rpe.challenger.view.dto.CargoCarDataDTO;
import br.rpe.challenger.view.dto.CargoCarViewDTO;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CargoCarServiceImplTest {
    @Mock
    private CargoCarRepository repository;

    @Spy
    private CargoCarMapper mapper;

    @InjectMocks
    @Spy
    private static CargoCarServiceImpl service;

    private static CargoCar entity;
    private static CargoCarDataDTO dataDTO;
    private static CargoCarViewDTO viewDTO;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "mapper", mapper);
    }

    @BeforeAll
    static void setUp() {
        entity = new CargoCar(1, "ABC123", "Name", "Brand", 50, 50);
        dataDTO = new CargoCarDataDTO("ABC123", "Name", "Brand", 50, 50);
        viewDTO = new CargoCarViewDTO(1, "ABC123", "Name", "Brand", 50, 50);
    }

    @Test
    void testShouldFindAll() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.cargoCarToCargoCarViewDTO(anyList())).thenReturn(List.of(viewDTO));

        List<CargoCarViewDTO> dtos = service.findAll();

        assertTrue(dtos.size() == 1);
        assertEquals(dtos.get(0).getId(), viewDTO.getId());

        verify(repository).findAll();
        verify(mapper).cargoCarToCargoCarViewDTO(anyList());
    }

    @Test
    void testShouldFindById() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(mapper.cargoCarToCargoCarViewDTO(any(CargoCar.class))).thenReturn(viewDTO);

        CargoCarViewDTO dto = assertDoesNotThrow(() -> service.findById(1));

        assertTrue(dto != null);
        assertEquals(dto, viewDTO);

        verify(repository).existsById(any());
        verify(repository).findById(any());
        verify(mapper).cargoCarToCargoCarViewDTO(any(CargoCar.class));
    }

    @Test
    void testShouldNotFindByIdWithInvalidId() {
        when(repository.existsById(any())).thenReturn(false);

        Throwable exception = assertThrows(CarNotFoundException.class, () -> service.findById(999));

        assertEquals("Could not find a cargo car with id = '999'", exception.getMessage());

        verify(repository).existsById(any());
        verify(repository, times(0)).findById(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void testShouldSave() {
        when(repository.existsByLicencePlate(anyString())).thenReturn(false);
        when(repository.save(any(CargoCar.class))).thenReturn(entity);
        when(mapper.cargoCarDataDTOToCargoCar(any())).thenReturn(entity);
        when(mapper.cargoCarToCargoCarViewDTO(any(CargoCar.class))).thenReturn(viewDTO);

        CargoCarViewDTO dto = assertDoesNotThrow(() -> service.save(dataDTO));

        assertTrue(dto != null);
        assertEquals(dto, viewDTO);

        verify(repository).existsByLicencePlate(anyString());
        verify(repository).save(any(CargoCar.class));
        verify(mapper).cargoCarDataDTOToCargoCar(any());
        verify(mapper).cargoCarToCargoCarViewDTO(any(CargoCar.class));
    }

    @Test
    void testShouldNotSaveWithAlreadyExistingLicencePlate() {
        when(repository.existsByLicencePlate(anyString())).thenReturn(true);

        Throwable exception = assertThrows(CarAlreadyExistsException.class, () -> service.save(dataDTO));

        assertEquals("A cargo car with licence plate = 'ABC123' already exists", exception.getMessage());

        verify(repository).existsByLicencePlate(anyString());
        verify(repository, times(0)).save(any(CargoCar.class));
        verifyNoInteractions(mapper);
    }

    @Test
    void testShouldUpdate() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any(CargoCar.class))).thenReturn(entity);
        when(mapper.cargoCarDataDTOToCargoCar(any())).thenReturn(entity);
        when(mapper.cargoCarToCargoCarViewDTO(any(CargoCar.class))).thenReturn(viewDTO);

        CargoCarViewDTO dto = assertDoesNotThrow(() -> service.update(1, dataDTO));

        assertTrue(dto != null);
        assertEquals(dto, viewDTO);

        verify(repository).existsById(any());
        verify(repository).save(any(CargoCar.class));
        verify(mapper).cargoCarDataDTOToCargoCar(any());
        verify(mapper).cargoCarToCargoCarViewDTO(any(CargoCar.class));
    }

    @Test
    void testShouldNotUpdateWithInvalidId() {
        when(repository.existsById(any())).thenReturn(false);

        Throwable exception = assertThrows(CarNotFoundException.class, () -> service.update(999, dataDTO));

        assertEquals("Could not find a cargo car with id = '999'", exception.getMessage());

        verify(repository).existsById(any());
        verify(repository, times(0)).save(any(CargoCar.class));
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

        assertEquals("Could not find a cargo car with id = '999'", exception.getMessage());

        verify(repository).existsById(any());
        verify(repository, times(0)).deleteById(any());
    }
}