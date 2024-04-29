package br.rpe.challenger.model.service;

import br.rpe.challenger.view.dto.CargoCarDataDTO;
import br.rpe.challenger.view.dto.CargoCarViewDTO;


import java.util.List;

public interface CargoCarService {
    public List<CargoCarViewDTO> findAll();

    public CargoCarViewDTO findById(Integer id);

    public CargoCarViewDTO save(CargoCarDataDTO dto);

    public CargoCarViewDTO update(Integer id, CargoCarDataDTO dto);

    public void delete(Integer id);
}
