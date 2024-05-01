package br.rpe.challenger.controller;


import br.rpe.challenger.model.entity.PassengerCar;
import br.rpe.challenger.model.repository.PassengerCarRepository;
import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PassengerCarControllerTest {

    @Autowired
    private PassengerCarRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String url = "/api/passenger-cars";

    private static PassengerCar entity;
    private static PassengerCarDataDTO dataDTO;
    private static PassengerCarViewDTO viewDTO;

    @BeforeAll
    static void setUp() {
        entity = new PassengerCar(1, "ABC123", "Name", "Brand", 5);
        viewDTO = new PassengerCarViewDTO(1, "ABC123", "Name", "Brand", 5);
    }

    @BeforeEach
    void beforeEach() {
        dataDTO = new PassengerCarDataDTO("ABC123", "Name", "Brand", 5);
        repository.deleteAll();
    }

    @Order(1)
    @Test
    void testShouldFindByIdAndReturn200Status() {
        PassengerCar savedEntity = repository.save(entity);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get(url + "/" + savedEntity.getId())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("name").value(viewDTO.getName()))
                    .andExpect(MockMvcResultMatchers.jsonPath("licencePlate").value(viewDTO.getLicencePlate()))
                    .andExpect(MockMvcResultMatchers.jsonPath("brand").value(viewDTO.getBrand()))
                    .andExpect(MockMvcResultMatchers.jsonPath("numberOfPassengers").value(viewDTO.getNumberOfPassengers()));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(2)
    @Test
    void testShouldNotFindByIdAndReturn404Status() {
        Integer invalidId = 9999;

        try {
            mockMvc.perform(MockMvcRequestBuilders.get(url + "/" + invalidId)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.jsonPath("timestamp").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("exception").value("CarNotFoundException"))
                    .andExpect(MockMvcResultMatchers.jsonPath("status").value(404));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(3)
    @Test
    void testShouldSaveAndReturn201Status() {
        try {
            String dtoAsString = objectMapper.writeValueAsString(dataDTO);

            mockMvc.perform(MockMvcRequestBuilders.post(url)
                            .content(dtoAsString)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("name").value(viewDTO.getName()))
                    .andExpect(MockMvcResultMatchers.jsonPath("licencePlate").value(viewDTO.getLicencePlate()))
                    .andExpect(MockMvcResultMatchers.jsonPath("brand").value(viewDTO.getBrand()))
                    .andExpect(MockMvcResultMatchers.jsonPath("numberOfPassengers").value(viewDTO.getNumberOfPassengers()));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(4)
    @Test
    void testShouldNotSaveWithExistingLicencePlateAndReturn409Status() {
        repository.save(entity);

        try {
            String dtoAsString = objectMapper.writeValueAsString(dataDTO);

            mockMvc.perform(MockMvcRequestBuilders.post(url)
                            .content(dtoAsString)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andExpect(MockMvcResultMatchers.jsonPath("timestamp").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("exception").value("CarAlreadyExistsException"))
                    .andExpect(MockMvcResultMatchers.jsonPath("status").value(409));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(5)
    @Test
    void testShouldNotSaveInvalidDtoAndReturn400Status() {
        dataDTO.setName("");

        try {
            String dtoAsString = objectMapper.writeValueAsString(dataDTO);

            mockMvc.perform(MockMvcRequestBuilders.post(url)
                            .content(dtoAsString)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("timestamp").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("exception").value("MethodArgumentNotValidException"))
                    .andExpect(MockMvcResultMatchers.jsonPath("status").value(400));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(6)
    @Test
    void testShouldUpdateAndReturn200Status() {
        PassengerCar savedEntity = repository.save(entity);

        dataDTO.setLicencePlate("BBB456");
        dataDTO.setName("New name");
        dataDTO.setBrand("New brand");
        dataDTO.setNumberOfPassengers(8);

        try {
            String dtoAsString = objectMapper.writeValueAsString(dataDTO);

            mockMvc.perform(MockMvcRequestBuilders.put(url + "/" + savedEntity.getId())
                            .content(dtoAsString)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("name").value("New name"))
                    .andExpect(MockMvcResultMatchers.jsonPath("licencePlate").value("BBB456"))
                    .andExpect(MockMvcResultMatchers.jsonPath("brand").value("New brand"))
                    .andExpect(MockMvcResultMatchers.jsonPath("numberOfPassengers").value(8));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(7)
    @Test
    void testShouldNotUpdateWithInvalidIdAndReturn404Status() {
        repository.save(entity);
        Integer invalidId = 9999;

        try {
            String dtoAsString = objectMapper.writeValueAsString(dataDTO);

            mockMvc.perform(MockMvcRequestBuilders.put(url + "/" + invalidId, dataDTO)
                            .content(dtoAsString)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.jsonPath("timestamp").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("exception").value("CarNotFoundException"))
                    .andExpect(MockMvcResultMatchers.jsonPath("status").value(404));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(8)
    @Test
    void testShouldNotUpdateInvalidDtoAndReturn400Status() {
        PassengerCar savedEntity = repository.save(entity);
        dataDTO.setName("");

        try {
            String dtoAsString = objectMapper.writeValueAsString(dataDTO);

            mockMvc.perform(MockMvcRequestBuilders.put(url + "/" + savedEntity.getId())
                            .content(dtoAsString)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("timestamp").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("exception").value("MethodArgumentNotValidException"))
                    .andExpect(MockMvcResultMatchers.jsonPath("status").value(400));
        } catch (Exception e) {
            fail();
        }
    }

    @Order(9)
    @Test
    void testShouldDeleteAndReturn204Status() {
        PassengerCar savedEntity = repository.save(entity);

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete(url + "/" + savedEntity.getId())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());
        } catch (Exception e) {
            fail();
        }
    }

    @Order(10)
    @Test
    void shouldNotDeleteWithInvalidIdAndReturn404Status() {
        repository.save(entity);
        Integer invalidId = 9999;

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete(url + "/" + invalidId)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.jsonPath("timestamp").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("exception").value("CarNotFoundException"))
                    .andExpect(MockMvcResultMatchers.jsonPath("status").value(404));
        } catch (Exception e) {
            fail();
        }
    }
}