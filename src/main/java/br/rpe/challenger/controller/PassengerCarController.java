package br.rpe.challenger.controller;

import br.rpe.challenger.model.service.PassengerCarService;
import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/passenger-cars")
public class PassengerCarController {
    private PassengerCarService service;
    @GetMapping
    public ResponseEntity<List<PassengerCarViewDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerCarViewDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PassengerCarViewDTO> save(@Valid PassengerCarDataDTO dto){
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerCarViewDTO> update(@PathVariable Integer id, @Valid PassengerCarDataDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
