package br.rpe.challenger.controller;

import br.rpe.challenger.model.service.PassengerCarService;
import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Passenger Car", description = "Endpoints for passenger car management")
@AllArgsConstructor
@RestController
@RequestMapping("/api/passenger-cars")
public class PassengerCarController {
    private PassengerCarService service;

    @Operation(
            summary = "Get all passenger cars",
            description = "Retrieve all saved passenger cars")
    @GetMapping
    public ResponseEntity<List<PassengerCarViewDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Get passenger car by id",
            description = "Retrieve a passenger car by id")
    @GetMapping("/{id}")
    public ResponseEntity<PassengerCarViewDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Post passenger car",
            description = "Save a passenger car")
    @PostMapping
    public ResponseEntity<PassengerCarViewDTO> save(@Valid @RequestBody PassengerCarDataDTO dto){
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Put passenger car",
            description = "Update passenger car information")
    @PutMapping("/{id}")
    public ResponseEntity<PassengerCarViewDTO> update(@PathVariable Integer id, @Valid @RequestBody PassengerCarDataDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }

    @Operation(
            summary = "Delete passenger car",
            description = "Delete a passenger car by id")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
