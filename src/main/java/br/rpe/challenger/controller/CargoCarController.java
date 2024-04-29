package br.rpe.challenger.controller;

import br.rpe.challenger.model.service.CargoCarService;
import br.rpe.challenger.view.dto.CargoCarDataDTO;
import br.rpe.challenger.view.dto.CargoCarViewDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cargo-cars")
public class CargoCarController {
    private CargoCarService service;
    @GetMapping
    public ResponseEntity<List<CargoCarViewDTO>> findAll(){return ResponseEntity.ok(service.findAll());}
    @GetMapping("/{id}")
    public ResponseEntity<CargoCarViewDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping
    public ResponseEntity<CargoCarViewDTO> save(@Valid @RequestBody CargoCarDataDTO dto){
        return new ResponseEntity<>(service.save(dto),HttpStatus.CREATED);

    }@PutMapping
    public ResponseEntity<CargoCarViewDTO> update(@PathVariable Integer id,@Valid @RequestBody CargoCarDataDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
