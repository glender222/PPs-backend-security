package com.example.demo.Mappers.MapperEspecialy;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.PPPDto;
import com.example.demo.entity.PPP;

@Component
public class PPPMapper {
    private final ModelMapper modelMapper;

    public PPPMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        
        modelMapper.typeMap(PPP.class, PPPDto.class)
            .setPreConverter(context -> {
                PPP source = context.getSource();
                PPPDto destination = new PPPDto();
                
                // Mapeo manual de campos
                destination.setId(source.getId());
                destination.setEstado(source.getEstado());
                destination.setModalidad(source.getModalidad());
                destination.setHoras(source.getHoras());
                destination.setFechaInicio(source.getFechaInicio());
                destination.setFechaFin(source.getFechaFin());
                
                // Mapeo de relaciones con null check
                if (source.getPracticante_EP() != null) {
                    destination.setPracticanteEpId(source.getPracticante_EP().getId());
                }
                
                if (source.getEmpresa() != null) {
                    destination.setRazonSocial(source.getEmpresa().getRazonSocial());
                    destination.setRuc(source.getEmpresa().getRuc());
                    destination.setDireccion(source.getEmpresa().getDireccion());
                }
                
                if (source.getArea_practicas() != null) {
                    destination.setNombreArea(source.getArea_practicas().getNombre());
                    destination.setDescripcionArea(source.getArea_practicas().getDescripcion());
                }
                
                if (source.getLinea() != null) {
                    destination.setLineaNombre(source.getLinea().getNombre());
                }
                
                return destination;
            });
    }

    public PPPDto toDto(PPP ppp) {
        try {
            if (ppp == null) return null;
            return modelMapper.map(ppp, PPPDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error en mapeo PPP->PPPDto: " + e.getMessage());
        }
    }

    public PPP toEntity(PPPDto dto) {
        try {
            if (dto == null) return null;
            return modelMapper.map(dto, PPP.class);
        } catch (Exception e) {
            throw new RuntimeException("Error en mapeo PPPDto->PPP: " + e.getMessage());
        }
    }
    }

