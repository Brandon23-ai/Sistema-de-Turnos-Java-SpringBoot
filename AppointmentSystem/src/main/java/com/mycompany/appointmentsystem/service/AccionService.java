/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.service;

import com.mycompany.appointmentsystem.datastructures.ColaDeTurnos;
import com.mycompany.appointmentsystem.datastructures.pila.Accion;
import com.mycompany.appointmentsystem.datastructures.pila.PilaDeAcciones;
import com.mycompany.appointmentsystem.entity.Turno;
import com.mycompany.appointmentsystem.enums.EstadoTurno;
import com.mycompany.appointmentsystem.repository.TurnoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class AccionService {

    private final PilaDeAcciones pilaDeAcciones;
    private final TurnoRepository turnoRepository;
    private final ColaDeTurnos colaDeTurnos;

    public void registrarAccion(Accion accion) {
        log.info("Registrando acción: {} para cliente ID: {}", accion.getTipoAccion(), accion.getClienteId());
        pilaDeAcciones.agregarAccion(accion.getClienteId(), accion);
    }

    @Transactional
    public String desHacerUltimaAccion(Long clienteId) {
        log.info("Intentando deshacer última acción para cliente ID: {}", clienteId);
        Accion accion = pilaDeAcciones.deshacerUltimaAccion(clienteId);

        if (accion == null) {
            log.warn("No hay acciones para deshacer para cliente ID: {}", clienteId);
            return "No hay acciones para deshacer";
        }

        try {
            switch (accion.getTipoAccion()) {
                case SOLICITAR_TURNO:
                    turnoRepository.deleteById(accion.getTurno().getId());
                    colaDeTurnos.removerTurno(accion.getTurno());
                    log.info("Se deshizo la solicitud del turno con ID: {}", accion.getTurno().getId());
                    return "Se deshizo la solicitud del turno";

                case ATENDER_TURNO:
                    Turno turnoAtendido = turnoRepository.findById(accion.getTurno().getId())
                            .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
                    turnoAtendido.setEstado(EstadoTurno.PENDIENTE);
                    turnoRepository.save(turnoAtendido);
                    colaDeTurnos.agregarTurno(turnoAtendido);
                    log.info("Se deshizo la atención del turno con ID: {}", turnoAtendido.getId());
                    return "Se deshizo la atención del turno";

                case CANCELAR_TURNO:
                    Turno turnoCancelado = turnoRepository.findById(accion.getTurno().getId())
                            .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
                    turnoCancelado.setEstado(EstadoTurno.PENDIENTE);
                    turnoRepository.save(turnoCancelado);
                    colaDeTurnos.agregarTurno(turnoCancelado);
                    log.info("Se deshizo la cancelación del turno con ID: {}", turnoCancelado.getId());
                    return "Se deshizo la cancelación del turno";

                default:
                    log.warn("Tipo de acción desconocida para cliente ID: {}", clienteId);
                    return "Tipo de acción desconocida";
            }
        } catch (Exception e) {
            log.error("Error al deshacer acción para cliente ID {}: {}", clienteId, e.getMessage());
            throw e;
        }
    }
}
