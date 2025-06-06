/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.service;


import com.mycompany.appointmentsystem.dto.ServicioDTO;
import com.mycompany.appointmentsystem.dto.ServicioRespuestaDTO;
import com.mycompany.appointmentsystem.entity.Servicio;
import com.mycompany.appointmentsystem.mapper.ServicioMapper;
import com.mycompany.appointmentsystem.messaging.producer.ServicioProducer;
import com.mycompany.appointmentsystem.repository.ServicioRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final ServicioProducer servicioProducer;

        @Transactional
    public ServicioDTO crearServicioPadre(ServicioDTO servicioDTO) {
        Servicio servicio = ServicioMapper.toEntity(servicioDTO);
        servicio.setPadre(null); // es raíz
        Servicio guardado = servicioRepository.save(servicio);
        
        log.info("Servicio padre creado con ID: {}", guardado.getId());
        
        try {
            servicioProducer.enviarServicioCreado(ServicioMapper.toDTO(guardado));
            log.info("Mensaje de servicio padre enviado a RabbitMQ.");
        } catch (Exception ex) {
            log.error("Error al enviar mensaje RabbitMQ: " + ex);
        }

        return ServicioMapper.toDTO(guardado);
    }

    @Transactional
    public ServicioDTO crearServicioHijo(ServicioDTO servicioDTO, Long idPadre) {
        Servicio servicio = ServicioMapper.toEntity(servicioDTO);
        
        Servicio padre = servicioRepository.findById(idPadre)
                .orElseThrow(() -> {
                    log.warn("No se encontró servicio padre con ID: {}", idPadre);
                    return new RuntimeException("Servicio padre no encontrado");
                });
        
        servicio.setPadre(padre);
        Servicio guardado = servicioRepository.save(servicio);
        
        log.info("Servicio hijo creado con ID: {} bajo el padre ID: {}", guardado.getId(), idPadre);

        try {
            servicioProducer.enviarServicioCreado(ServicioMapper.toDTO(guardado));
            log.info("Mensaje de servicio hijo enviado a RabbitMQ.");
        } catch (Exception ex) {
            log.error("Error al enviar mensaje RabbitMQ: " + ex);
        }

        return ServicioMapper.toDTO(guardado);
    }
    
    public List<ServicioRespuestaDTO> listarServiciosEnArbolPlano(Long idPadre) {
        Servicio padre = servicioRepository.findById(idPadre)
                .orElseThrow(() -> {
                    log.warn("No se encontró servicio raíz con ID: {}", idPadre);
                    return new RuntimeException("Servicio padre no encontrado con ID: " + idPadre);
                });
        
        log.info("Listando servicios en árbol plano desde ID raíz: {}", idPadre);
        
        List<ServicioRespuestaDTO> resultado = new ArrayList<>();
        recorrerRecursivo(padre, 0, resultado);
        
        log.info("Total de servicios en árbol plano: {}", resultado.size());
        
        return resultado;
    }
    
    private void recorrerRecursivo(Servicio servicio, int nivel, List<ServicioRespuestaDTO> resultado) {
        resultado.add(ServicioRespuestaDTO.builder()
                .id(servicio.getId())
                .nombre(servicio.getNombre())
                .descripcion(servicio.getDescripcion())
                .nivel(nivel)
                .build());

        // Obtener hijos de este servicio (si existen)
        List<Servicio> hijos = servicioRepository.findAll()
                .stream()
                .filter(s -> s.getPadre() != null && s.getPadre().getId().equals(servicio.getId()))
                .toList();

        for (Servicio hijo : hijos) {
            recorrerRecursivo(hijo, nivel + 1, resultado);
        }
    }
  
}
