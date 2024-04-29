package br.rpe.challenger.model.service;

import br.rpe.challenger.model.entity.CargoCar;
import br.rpe.challenger.model.repository.CargoCarRepository;
import br.rpe.challenger.view.dto.CargoCarDataDTO;
import br.rpe.challenger.view.dto.CargoCarViewDTO;
import br.rpe.challenger.view.exception.CarAlreadyExistsException;
import br.rpe.challenger.view.exception.CarNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CargoCarServiceImpl implements CargoCarService{
    private CargoCarRepository repository;
    @Autowired
    private CargoCarMapper mapper;

    @Override
    public List<CargoCarViewDTO> findAll() {
        return mapper.cargoCarToCargoCarViewDTO(repository.findAll());
    }
    @SneakyThrows
    @Override
    public CargoCarViewDTO findById(Integer id) {
        if(!repository.existsById(id)){
            throw new CarNotFoundException("cargo car", "id", id.toString());
        }
        return mapper.cargoCarToCargoCarViewDTO(repository.findById(id).get());
    }

    @SneakyThrows
    @Override
    public CargoCarViewDTO save(CargoCarDataDTO dto) {
        if(repository.existsByLicencePlate(dto.getLicencePlate())){
            throw new CarAlreadyExistsException("cargo car", "licence plate", dto.getLicencePlate());
        }
        CargoCar entity=mapper.cargoCarDataDTOToCargoCar(dto);
        return mapper.cargoCarToCargoCarViewDTO(repository.save((entity)));
        }
    @SneakyThrows
    @Override
    public CargoCarViewDTO update(Integer id, CargoCarDataDTO dto) {
        if(!repository.existsById(id)){
            throw new CarNotFoundException("cargo car", "id", id.toString());
        }
        CargoCar entity = mapper.cargoCarDataDTOToCargoCar(dto);
        entity.setId(id);
        return mapper.cargoCarToCargoCarViewDTO(repository.save(entity));
    }

    @SneakyThrows
    @Override
    public void delete(Integer id){
        if(!repository.existsById(id)){
            throw new CarNotFoundException("passenger car", "id", id.toString());
        }
        repository.deleteById(id);
    }
}
