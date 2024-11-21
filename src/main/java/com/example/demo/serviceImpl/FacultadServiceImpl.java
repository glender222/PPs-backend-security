package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.FacultadDTO;
import com.example.demo.Mappers.FacultadMapper;
import com.example.demo.entity.Facultad;
import com.example.demo.repository.CampusRepository;
import com.example.demo.repository.FacultadRepository;
import com.example.demo.service.FacultadService;





@Service
public class FacultadServiceImpl implements FacultadService {

	private final FacultadRepository facultadRepository;
    private final FacultadMapper facultadMapper;
    private final CampusRepository campusRepository;

  public FacultadServiceImpl(FacultadRepository facultadRepository, FacultadMapper facultadMapper, CampusRepository campusRepository) {
        this.facultadRepository = facultadRepository;
        this.facultadMapper = facultadMapper;
        this.campusRepository = campusRepository;
    }


	@Override
	public FacultadDTO create(FacultadDTO facultadDTO) {
		Facultad facultad = facultadMapper.toEntity(facultadDTO);
        facultad.setCampus(campusRepository.findById(facultadDTO.getIdCampus())
                .orElseThrow(() -> new RuntimeException("Campus no encontrado")));
        return facultadMapper.toDto(facultadRepository.save(facultad));
	}

	@Override
	public FacultadDTO update(FacultadDTO facultadDTO) {
		Facultad facultad = facultadMapper.toEntity(facultadDTO);
        facultad.setCampus(campusRepository.findById(facultadDTO.getIdCampus())
                .orElseThrow(() -> new RuntimeException("Campus no encontrado")));
        return facultadMapper.toDto(facultadRepository.save(facultad));
	}

	@Override
	public void delete(Long id) {
		facultadRepository.deleteById(id);
	}

	@Override
	public FacultadDTO findById(Long id) {
		return facultadRepository.findById(id).map(facultadMapper::toDto).orElse(null);
	}

	@Override
	public List<FacultadDTO> findAll() {
		 return facultadRepository.findAll().stream().map(facultadMapper::toDto).collect(Collectors.toList());
	}


}
