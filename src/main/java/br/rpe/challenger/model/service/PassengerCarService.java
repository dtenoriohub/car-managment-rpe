package br.rpe.challenger.model.service;

import br.rpe.challenger.view.dto.PassengerCarDataDTO;
import br.rpe.challenger.view.dto.PassengerCarViewDTO;

import java.util.List;

public interface PassengerCarService {
    public List<PassengerCarViewDTO> findAll();
    public PassengerCarViewDTO findById(Integer id);
    public PassengerCarViewDTO save(PassengerCarDataDTO dto);
    public PassengerCarViewDTO update(Integer id,PassengerCarDataDTO dto);
    public void delete(Integer id);
}
