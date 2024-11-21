package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.example.demo.Dto.CampusDTO;
import com.example.demo.Mappers.CampusMapper;
import com.example.demo.entity.Campus;
import com.example.demo.repository.CampusRepository;
import com.example.demo.service.CampusService;



@Service

public class CampusServiceImpl implements CampusService {

	private final CampusRepository campusRepository;
    private final CampusMapper campusMapper;

 public CampusServiceImpl(CampusRepository campusRepository, CampusMapper campusMapper) {
        this.campusRepository = campusRepository;
        this.campusMapper = campusMapper;
    }


	@Override
	public CampusDTO create(CampusDTO campusDTO) {
		Campus campus = campusMapper.toEntity(campusDTO);
        return campusMapper.toDto(campusRepository.save(campus));
	}

	@Override
	public CampusDTO update(CampusDTO campusDTO) {
		Campus campus = campusMapper.toEntity(campusDTO);
        return campusMapper.toDto(campusRepository.save(campus));
	}

	@Override
	public void delete(Long id) {
		campusRepository.deleteById(id);
	}

	@Override
	public CampusDTO findById(Long id) {
		return campusRepository.findById(id).map(campusMapper::toDto).orElse(null);

	}

	@Override
	public List<CampusDTO> findAll() {
		return campusRepository.findAll().stream().map(campusMapper::toDto).collect(Collectors.toList());

    }
	

}
