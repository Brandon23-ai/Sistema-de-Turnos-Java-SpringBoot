/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.service;

import com.mycompany.appointmentsystem.datastructures.ColaDeTurnos;
import com.mycompany.appointmentsystem.datastructures.ListaHistorial;
import com.mycompany.appointmentsystem.datastructures.pila.Accion;
import com.mycompany.appointmentsystem.datastructures.pila.TipoAccion;
import com.mycompany.appointmentsystem.enums.EstadoTurno;
import com.mycompany.appointmentsystem.dto.TurnoDTO;
import com.mycompany.appointmentsystem.entity.Cliente;
import com.mycompany.appointmentsystem.entity.Servicio;
import com.mycompany.appointmentsystem.entity.Turno;
import com.mycompany.appointmentsystem.mapper.TurnoMapper;
import com.mycompany.appointmentsystem.messaging.producer.TurnoProducer;
import com.mycompany.appointmentsystem.repository.ClienteRepository;
import com.mycompany.appointmentsystem.repository.ServicioRepository;
import com.mycompany.appointmentsystem.repository.TurnoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class TurnoService {
    
    private final TurnoRepository turnoRepository;
    private final ServicioRepository servicioRespository;
    private final ClienteRepository clienteRepository;
    private final ColaDeTurnos colaDeTurnos;
    private final ListaHistorial listaHistorial;
    private final AccionService accionService;
    private final TurnoProducer turnoProducer;
    
    //solicitar un turno
    @Transactional
    public TurnoDTO solicitarTurno(TurnoDTO dto) {    
        Servicio servicio = servicioRespository.findById(dto.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Turno turno = TurnoMapper.toEntity(dto, servicio, cliente);
        Turno guardado = turnoRepository.save(turno);
        
        log.info("Turno solicitado: {} para cliente {}", guardado.getId(), cliente.getNombre());
        
        colaDeTurnos.agregarTurno(guardado);
        Accion accion = new Accion(cliente.getId(), TipoAccion.SOLICITAR_TURNO, guardado);
        accionService.registrarAccion(accion);
        
        try {
            turnoProducer.enviarTurnoCreado(TurnoMapper.toDTO(guardado));
        } catch (Exception e) {
            log.error("Error al enviar a rabbitMQ: "+ e);
        }

        return TurnoMapper.toDTO(guardado);
    }
    
    // 2. Atender el siguiente turno en orden (FIFO)
    @Transactional
    public TurnoDTO atenderSiguienteTurno() {
        Turno turno = colaDeTurnos.siguienteTurno();
        if (turno == null) {
            log.warn("Intento de atender turno pero la cola esta vacia");
            throw new RuntimeException("No hay turnos pendientes.");
        }
        
        turno.setEstado(EstadoTurno.ATENDIDO);
        Turno actualizado = turnoRepository.save(turno);
        
        log.info("Turno atendido: {}", actualizado.getId());
        
        listaHistorial.agregarTurno(turno.getCliente().getId(), actualizado);
        Accion accion = new Accion(turno.getCliente().getId(), TipoAccion.ATENDER_TURNO, actualizado);
        accionService.registrarAccion(accion);
        
        try {
            turnoProducer.enviarTurnoAtendido(TurnoMapper.toDTO(actualizado));
            log.info("Turno atendido enviado a RabbitMQ: {}", actualizado.getId());
        } catch (Exception e) {
            log.error("Error al enviar atendido: " + e);
        }
        
        return TurnoMapper.toDTO(actualizado);
    }
    
    // 3. Cancelar un turno por ID (si está pendiente)
    @Transactional
    public TurnoDTO cancelarTurno(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        if (turno.getEstado() != EstadoTurno.PENDIENTE) {
            log.warn("Intento de cancelar turno que no está pendiente: {}", id);
            throw new RuntimeException("Solo se pueden cancelar turnos pendientes.");
        }

        turno.setEstado(EstadoTurno.CANCELADO);
        Turno cancelado = turnoRepository.save(turno);
        
        log.info("Turno cancelado: {}", cancelado.getId());
        
        colaDeTurnos.removerTurno(cancelado);
        
        Accion accion = new Accion(turno.getCliente().getId(), TipoAccion.CANCELAR_TURNO, cancelado);
        accionService.registrarAccion(accion);
        
        try {
            turnoProducer.enviarTurnoCancelado(TurnoMapper.toDTO(cancelado));
            log.info("Turno cancelado enviado a RabbitMQ: {}", cancelado.getId());
        } catch (Exception e) {
            log.error("Error al enviar cancealdo: " + e);
        }

        return TurnoMapper.toDTO(cancelado);
    }
}
