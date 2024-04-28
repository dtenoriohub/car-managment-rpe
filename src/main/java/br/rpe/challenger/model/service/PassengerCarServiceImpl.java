package br.rpe.challenger.model.service;

import br.rpe.challenger.model.entity.PassengerCar;
import br.rpe.challenger.model.repository.PassengerCarRepository;
import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;
import br.rpe.challenger.view.exception.CarAlreadyExistsException;
import br.rpe.challenger.view.exception.CarNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PassengerCarServiceImpl implements PassengerCarService{
    private PassengerCarRepository repository;
    @Autowired
    private PassengerCarMapper mapper;


    @Override
    public List<PassengerCarViewDTO> findAll() {
        return mapper.passengerCarToPassengerCarViewDTO(repository.findAll());
    }

    @SneakyThrows
    @Override
    public PassengerCarViewDTO findById(Integer id)  {
        if(!repository.existsById(id)){
           throw new CarNotFoundException("passenger car", "id", id.toString());
        }
        return mapper.passengerCarToPassengerCarViewDTO(repository.findById(id).get());
    }

    @SneakyThrows
    @Override
    public PassengerCarViewDTO save(PassengerCarDataDTO dto){
        if(repository.existsByLicencePlate(dto.getLicencePlate())){
            throw new CarAlreadyExistsException("passenger car", "licence plate", dto.getLicencePlate());
        }
        PassengerCar entity = mapper.passengerCarDataDTOToPassengerCar(dto);
        return mapper.passengerCarToPassengerCarViewDTO(repository.save(entity));
    }

    @SneakyThrows
    @Override
    public PassengerCarViewDTO update(Integer id, PassengerCarDataDTO dto){
        if(!repository.existsById(id)){
            throw new CarNotFoundException("passenger car", "id", id.toString());
        }

        PassengerCar entity = mapper.passengerCarDataDTOToPassengerCar(dto);
        return mapper.passengerCarToPassengerCarViewDTO(repository.save(entity));
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