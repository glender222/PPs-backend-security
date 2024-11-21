package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.EscuelaProfesionalDTO;
import com.example.demo.Mappers.EscuelaProfesionalMapper;
import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.repository.EscuelaProfesionalRepository;
import com.example.demo.repository.FacultadRepository;
import com.example.demo.service.EscuelaProfesionalService;


@Service
public class EscuelaProfesionalServiceImpl implements EscuelaProfesionalService {
private final EscuelaProfesionalRepository escuelaProfesionalRepository;
    private final EscuelaProfesionalMapper escuelaProfesionalMapper;
    private final FacultadRepository facultadRepository;

    public EscuelaProfesionalServiceImpl(EscuelaProfesionalRepository escuelaProfesionalRepository, EscuelaProfesionalMapper escuelaProfesionalMapper, FacultadRepository facultadRepository) {
        this.escuelaProfesionalRepository = escuelaProfesionalRepository;
        this.escuelaProfesionalMapper = escuelaProfesionalMapper;
        this.facultadRepository = facultadRepository;
    }




	@Override
	public EscuelaProfesionalDTO create(EscuelaProfesionalDTO escuelaDTO) {
	  EscuelaProfesional escuela = escuelaProfesionalMapper.toEntity(escuelaDTO);
        escuela.setFacultad(facultadRepository.findById(escuelaDTO.getIdFacultad())
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada")));
        return escuelaProfesionalMapper.toDto(escuelaProfesionalRepository.save(escuela));
	}

	@Override
	public EscuelaProfesionalDTO update(EscuelaProfesionalDTO escuelaDTO) {
		EscuelaProfesional escuela = escuelaProfesionalMapper.toEntity(escuelaDTO);
        escuela.setFacultad(facultadRepository.findById(escuelaDTO.getIdFacultad())
                .orElseThrow(() -> new RuntimeException("Facultad no encontrada")));
        return escuelaProfesionalMapper.toDto(escuelaProfesionalRepository.save(escuela));
	}

	@Override
	public void delete(Long id) {
		escuelaProfesionalRepository.deleteById(id);

	}

	@Override
	public EscuelaProfesionalDTO findById(Long id) {
		return escuelaProfesionalRepository.findById(id).map(escuelaProfesionalMapper::toDto).orElse(null);

	}

	@Override
	public List<EscuelaProfesionalDTO> findAll() {
		        return escuelaProfesionalRepository.findAll().stream().map(escuelaProfesionalMapper::toDto).collect(Collectors.toList());

	}

	@Override
	public List<EscuelaProfesionalDTO> findByFacultadId(Long facultadId) {
		return escuelaProfesionalRepository.findByFacultadId(facultadId).stream().map(escuelaProfesionalMapper::toDto).collect(Collectors.toList());

	}


}
