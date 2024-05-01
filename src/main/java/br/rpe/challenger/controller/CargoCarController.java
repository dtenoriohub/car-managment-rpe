package br.rpe.challenger.controller;

import br.rpe.challenger.model.service.CargoCarService;
import br.rpe.challenger.view.dto.CargoCarDataDTO;
import br.rpe.challenger.view.dto.CargoCarViewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cargo Car", description = "Endpoints for cargo car management")
@AllArgsConstructor
@RestController
@RequestMapping("/api/cargo-cars")
public class CargoCarController {
    private CargoCarService service;

    @Operation(
            summary = "Get all cargo cars",
            description = "Retrieve all saved cargo cars")
    @GetMapping
    public ResponseEntity<List<CargoCarViewDTO>> findAll(){return ResponseEntity.ok(service.findAll());}

    @Operation(
            summary = "Get cargo car by id",
            description = "Retrieve a cargo car by id")
    @GetMapping("/{id}")
    public ResponseEntity<CargoCarViewDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Post cargo car",
            description = "Save a cargo car")
    @PostMapping
    public ResponseEntity<CargoCarViewDTO> save(@Valid @RequestBody CargoCarDataDTO dto){
        return new ResponseEntity<>(service.save(dto),HttpStatus.CREATED);
    }

    @Operation(
            summary = "Put cargo car",
            description = "Update cargo car information")
    @PutMapping("/{id}")
    public ResponseEntity<CargoCarViewDTO> update(@PathVariable Integer id,@Valid @RequestBody CargoCarDataDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }

    @Operation(
            summary = "Delete cargo car",
            description = "Delete a cargo car by id")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}