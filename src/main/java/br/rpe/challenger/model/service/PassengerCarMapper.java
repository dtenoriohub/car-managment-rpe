package br.rpe.challenger.model.service;

import br.rpe.challenger.model.entity.PassengerCar;
import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerCarMapper {
    @Mapping(target = "id", ignore = true)
    PassengerCar passengerCarDataDTOToPassengerCar(PassengerCarDataDTO dto);
    PassengerCarViewDTO passengerCarToPassengerCarViewDTO(PassengerCar entity);
    List<PassengerCarViewDTO> passengerCarToPassengerCarViewDTO(List<PassengerCar> entity);
}
