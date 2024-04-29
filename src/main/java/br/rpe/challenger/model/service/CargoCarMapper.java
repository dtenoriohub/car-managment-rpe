package br.rpe.challenger.model.service;

import br.rpe.challenger.model.entity.CargoCar;
import br.rpe.challenger.model.entity.PassengerCar;
import br.rpe.challenger.view.dto.CargoCarDataDTO;
import br.rpe.challenger.view.dto.CargoCarViewDTO;
import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CargoCarMapper {
    @Mapping(target = "id", ignore = true)
    CargoCar cargoCarDataDTOToCargoCar(CargoCarDataDTO dto);
   CargoCarViewDTO cargoCarToCargoCarViewDTO(CargoCar entity);
    List<CargoCarViewDTO> cargoCarToCargoCarViewDTO(List<CargoCar> entity);

}
