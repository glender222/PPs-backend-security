package com.example.demo.Mappers.MapperEspecialy;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.DatosPPPDto;
import com.example.demo.Dto.PPPDto;
import com.example.demo.entity.PPP;
import com.example.demo.entity.Representante;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.entity.Empresa;  // Añadir esta importación
import com.example.demo.entity.AreaPracticas;  // Añadir esta importación
@Slf4j
@Component
public class PPPMapper {
    private final ModelMapper modelMapper;

    public PPPMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        
        modelMapper.typeMap(PPP.class, PPPDto.class)
            .setPreConverter(context -> {
                PPP source = context.getSource();
                PPPDto destination = new PPPDto();
                
                try {
                    // Si es una nueva PPP, establecemos valores por defecto
                    if (source.getId() == null) {
                        destination.setEstado("PENDIENTE");
                        destination.setHoras(0);
                        return destination;
                    }
                    
                    // Mapeos básicos con verificación null
                    destination.setId(source.getId());
                    destination.setEstado(source.getEstado());
                    destination.setModalidad(source.getModalidad());
                    destination.setHoras(source.getHoras());
                    
                    // Mapeo de fechas con verificación null
                    if (source.getFechaInicio() != null) {
                        destination.setFechaInicio(source.getFechaInicio());
                    }
                    if (source.getFechaFin() != null) {
                        destination.setFechaFin(source.getFechaFin());
                    }
                    
                    // Mapeo de empresa con verificación null
                    if (source.getEmpresa() != null) {
                        destination.setRazonSocial(source.getEmpresa().getRazonSocial());
                        destination.setRuc(source.getEmpresa().getRuc());
                        destination.setDireccion(source.getEmpresa().getDireccion());
                        
                        // Verificación adicional para representantes
                        if (source.getEmpresa().getRepresentantes() != null && 
                            !source.getEmpresa().getRepresentantes().isEmpty()) {
                            Representante representante = source.getEmpresa().getRepresentantes().iterator().next();
                            destination.setNombreRepresentante(representante.getNombre());
                            destination.setApellidoRepresentante(representante.getApellido());
                            destination.setCargoRepresentante(representante.getCargo());
                            destination.setTelefonoRepresentante(representante.getTelefono());
                            destination.setCorreoRepresentante(representante.getCorreo_elec());
                        }
                    }
                    
                    // Mapeo de área con verificación null
                    if (source.getArea_practicas() != null) {
                        destination.setNombreArea(source.getArea_practicas().getNombre());
                        destination.setDescripcionArea(source.getArea_practicas().getDescripcion());
                    }

                    // Mapeo de línea con verificación null
                    if (source.getLinea() != null) {
                        destination.setLineaNombre(source.getLinea().getNombre());
                    }

                    // Mapeo de practicanteEP con verificación null
                    if (source.getPracticante_EP() != null) {
                        destination.setPracticanteEpId(source.getPracticante_EP().getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // En caso de error, retornamos un DTO con valores mínimos
                    destination.setEstado("ERROR");
                    return destination;
                }
                
                return destination;
            });
    }

    public PPPDto toDto(PPP ppp) {
        if (ppp == null) return null;
        try {
            PPPDto dto = modelMapper.map(ppp, PPPDto.class);
            
            // Mapeo adicional solo para el representante
            if (ppp.getEmpresa() != null && 
                ppp.getEmpresa().getRepresentantes() != null && 
                !ppp.getEmpresa().getRepresentantes().isEmpty()) {
                
                Representante rep = ppp.getEmpresa().getRepresentantes().iterator().next();
                dto.setNombreRepresentante(rep.getNombre());
                dto.setApellidoRepresentante(rep.getApellido());
                dto.setCargoRepresentante(rep.getCargo());
                dto.setTelefonoRepresentante(rep.getTelefono());
                dto.setCorreoRepresentante(rep.getCorreo_elec());
            }
            
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Error en mapeo PPP->PPPDto: " + e.getMessage());
        }
    }

    public PPP fromDto(PPPDto dto) {
        if (dto == null) return null;
        try {
            PPP ppp = new PPP();
            // Mapeo básico para nueva entidad
            ppp.setEstado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE");
            ppp.setModalidad(dto.getModalidad());
            ppp.setHoras(dto.getHoras() != null ? dto.getHoras() : 0);
            ppp.setFechaInicio(dto.getFechaInicio());
            ppp.setFechaFin(dto.getFechaFin());
            return ppp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear PPP desde DTO: " + e.getMessage(), e);
        }
    }

    public void updatePPPFromDatos(PPP ppp, DatosPPPDto datos) {
        if (datos == null) return;

        try {
            // Actualizar empresa
            if (ppp.getEmpresa() == null) {
                ppp.setEmpresa(new Empresa());
            }
            ppp.getEmpresa().setRazonSocial(datos.getRazonSocial());
            ppp.getEmpresa().setRuc(datos.getRuc());
            ppp.getEmpresa().setDireccion(datos.getDireccion());
            ppp.getEmpresa().setDescripcion(datos.getDescripcion());

            // Actualizar área de prácticas
            if (ppp.getArea_practicas() == null) {
                ppp.setArea_practicas(new AreaPracticas());
            }
            ppp.getArea_practicas().setNombre(datos.getNombreArea());
            ppp.getArea_practicas().setDescripcion(datos.getDescripcionArea());

        } catch (Exception e) {
            throw new RuntimeException("Error actualizando PPP desde datos: " + e.getMessage());
        }
    }
}