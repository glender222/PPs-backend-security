package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.DatosPPPDto;
import com.example.demo.Dto.PPPDto;
import com.example.demo.Mappers.MapperEspecialy.PPPMapper;
import com.example.demo.entity.AreaPracticas;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.Linea;
import com.example.demo.entity.PPP;
import com.example.demo.entity.Practicante;
import com.example.demo.entity.Practicante_EP;
import com.example.demo.entity.Representante;
import com.example.demo.login.Enum.EstadoPPP;
import com.example.demo.repository.PPPRepository;
import com.example.demo.repository.PracticanteRepository;
import com.example.demo.repository.Practicante_EPRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;

@Slf4j

@Service
@Transactional
@RequiredArgsConstructor
public class PPPService {
	private final PPPRepository pppRepository;
    private final Practicante_EPRepository practicanteEPRepository;
    private final PPPMapper pppMapper;
    private final EmpresaService empresaService;
    private final AreaPracticasService areaPracticasService;
    private final PracticanteRepository practicanteRepository;
    private final LineaService lineaService;



    public PPPDto comenzarPPP(Long personaId) {
        try {
            Practicante practicante = practicanteRepository.findByPersonaId(personaId)
                .orElseThrow(() -> new RuntimeException("No existe un practicante registrado para este usuario"));

            Practicante_EP practicanteEP = practicanteEPRepository.findByPracticanteId(practicante.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el registro de practicante en la EP"));

            Optional<PPP> pppExistente = pppRepository.findByPracticante_EP(practicanteEP);
            if (pppExistente.isPresent()) {
                PPP ppp = pppExistente.get();
                if (!ppp.getEstado().equals(EstadoPPP.SIN_CARTA.getValor())) {
                    throw new RuntimeException("Ya existe una PPP activa para este practicante");
                }
                return pppMapper.toDto(ppp);
            }

            PPP ppp = new PPP();
            ppp.setPracticante_EP(practicanteEP);
            ppp.setEstado(EstadoPPP.SIN_CARTA.getValor());

            PPP savedPPP = pppRepository.save(ppp);
            
            return pppMapper.toDto(savedPPP);
        } catch (Exception e) {
            log.error("Error al crear PPP: ", e);
            throw new RuntimeException("Error al crear PPP: " + e.getMessage());
        }
    }


    public PPPDto obtenerPPPPorPersonaId(Long personaId) {
        try {
            // Primero buscar el practicante
            Practicante practicante = practicanteRepository.findByPersonaId(personaId)
                .orElseThrow(() -> new RuntimeException("No se encontró el practicante asociado a esta persona"));

            // Luego buscar el practicante_EP
            Practicante_EP practicanteEP = practicanteEPRepository.findByPracticanteId(practicante.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el registro de practicante en la EP"));

            // Finalmente buscar la PPP
            PPP ppp = pppRepository.findByPracticante_EP(practicanteEP)
                .orElseThrow(() -> new RuntimeException("No se encontró una PPP activa"));

            return pppMapper.toDto(ppp);
        } catch (Exception e) {
            log.error("Error al obtener PPP: ", e);
            throw new RuntimeException("Error al obtener la PPP: " + e.getMessage());
        }
    }

    public List<PPPDto> obtenerPPPPorEstado(String estado) {
        return pppRepository.findByEstado(estado).stream()
            .map(pppMapper::toDto)
            .collect(Collectors.toList());
    }

    public void actualizarEstadoPPP(Long id, String nuevoEstado) {
        PPP ppp = pppRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PPP no encontrada"));

        // Validaciones de transición de estados
        String estadoActual = ppp.getEstado();

        if (nuevoEstado.equals(EstadoPPP.APROBADA.getValor()) && 
            !estadoActual.equals(EstadoPPP.PENDIENTE.getValor())) {
            throw new RuntimeException("Solo se pueden aprobar PPPs en estado PENDIENTE");
        }

        if (nuevoEstado.equals(EstadoPPP.SIN_CARTA.getValor()) && 
            !estadoActual.equals(EstadoPPP.PENDIENTE.getValor())) {
            throw new RuntimeException("Solo se pueden rechazar PPPs en estado PENDIENTE");
        }

        ppp.setEstado(nuevoEstado);
        pppRepository.save(ppp);
    }

    public boolean existePPPActiva(Long personaId) {
        try {
            // Primero buscar el practicante
            Practicante practicante = practicanteRepository.findByPersonaId(personaId)
                .orElseThrow(() -> new RuntimeException("No se encontró el practicante asociado a esta persona"));

            // Luego buscar el practicante_EP
            Practicante_EP practicanteEP = practicanteEPRepository.findByPracticanteId(practicante.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el registro de practicante en la EP"));

            // Finalmente verificar si existe PPP
            return pppRepository.findByPracticante_EP(practicanteEP).isPresent();
        } catch (Exception e) {
            log.error("Error al verificar PPP activa: ", e);
            return false; // En caso de error, asumimos que no hay PPP activa
        }
    }

  public PPPDto guardarDatosPPP(Long pppId, DatosPPPDto datos) {
	validateDatosPPP(datos); // Nuevo método de validación
	
        try {
            PPP ppp = pppRepository.findById(pppId)
                .orElseThrow(() -> new RuntimeException("PPP no encontrada"));

            if (!ppp.getEstado().equals(EstadoPPP.SIN_CARTA.getValor())) {
                throw new RuntimeException("La PPP debe estar en estado SIN_CARTA");
            }

            // 1. Crear y guardar empresa
            Empresa empresa = new Empresa();
            empresa.setRazonSocial(datos.getRazonSocial());
            empresa.setDireccion(datos.getDireccion());
            empresa.setRuc(datos.getRuc());
            empresa.setDescripcion(datos.getDescripcion());

            // 2. Crear representante y establecer relación bidireccional
            Representante representante = new Representante();
            representante.setNombre(datos.getNombreRepresentante());
            representante.setApellido(datos.getApellidoRepresentante());
            representante.setCargo(datos.getCargoRepresentante());
            representante.setTelefono(datos.getTelefonoRepresentante());
            representante.setCorreo_elec(datos.getCorreoRepresentante());
            
            // Establecer relación bidireccional
            empresa.addRepresentante(representante);
            
            // 3. Guardar empresa (cascadeará al representante)
            empresa = empresaService.save(empresa);

            // 4. Crear y guardar área
            AreaPracticas area = new AreaPracticas();
            area.setNombre(datos.getNombreArea());
            area.setDescripcion(datos.getDescripcionArea());
            area = areaPracticasService.save(area);

            // 5. Actualizar PPP
            ppp.setEmpresa(empresa);
            ppp.setArea_practicas(area);
            ppp.setEstado(EstadoPPP.PENDIENTE.getValor());

            // 6. Asignar línea si existe
            if (datos.getNombreLinea() != null && !datos.getNombreLinea().isEmpty()) {
                Linea linea = lineaService.findByNombre(datos.getNombreLinea())
                    .orElseThrow(() -> new RuntimeException("Línea no encontrada: " + datos.getNombreLinea()));
                ppp.setLinea(linea);
            }

            // 7. Guardar PPP actualizada
            ppp = pppRepository.save(ppp);
            
            // 8. Mapear a DTO y retornar
            return pppMapper.toDto(ppp);

        } catch (Exception e) {
            log.error("Error guardando datos de PPP: ", e);
            throw new RuntimeException("Error al guardar los datos: " + e.getMessage());
        }
    }

	public boolean validarDatosCompletos(Long pppId) {
        PPP ppp = pppRepository.findById(pppId)
            .orElseThrow(() -> new RuntimeException("PPP no encontrada"));
        
        // Validar que existan todos los datos necesarios
        return ppp.getEmpresa() != null &&
               ppp.getArea_practicas() != null &&
               ppp.getEmpresa().getRepresentantes() != null &&
               !ppp.getEmpresa().getRepresentantes().isEmpty();
    }

    private void validateDatosPPP(DatosPPPDto datos) {
        if (datos == null) {
            throw new RuntimeException("Los datos no pueden ser nulos");
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<DatosPPPDto>> violations = validator.validate(datos);
        
        if (!violations.isEmpty()) {
            String errorMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
            throw new RuntimeException("Errores de validación: " + errorMessages);
        }
    }
}