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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AccionService {
    
    private final PilaDeAcciones pilaDeAcciones;
    private final TurnoRepository turnoRepository;
    private final ColaDeTurnos colaDeTurnos;
    
    public void registrarAccion(Accion accion){
        pilaDeAcciones.agregarAccion(accion.getClienteId(), accion);
    }
    
    @Transactional
    public String desHacerUltimaAccion(Long clienteId) {
        Accion accion = pilaDeAcciones.deshacerUltimaAccion(clienteId);

        if (accion == null) {
            return "No hay acciones para deshacer";
        }

        switch (accion.getTipoAccion()) {
            case SOLICITAR_TURNO:
                // Eliminar el turno creado
                turnoRepository.deleteById(accion.getTurno().getId());
                colaDeTurnos.removerTurno(accion.getTurno()); // si está en la cola
                return "Se deshizo la solicitud del turno";

            case ATENDER_TURNO:
                // Revertir a PENDIENTE y devolver a la cola
                Turno turnoAtendido = turnoRepository.findById(accion.getTurno().getId())
                        .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
                turnoAtendido.setEstado(EstadoTurno.PENDIENTE);
                turnoRepository.save(turnoAtendido);
                colaDeTurnos.agregarTurno(turnoAtendido);
                return "Se deshizo la atención del turno";

            case CANCELAR_TURNO:
                // Revertir a PENDIENTE y devolver a la cola
                Turno turnoCancelado = turnoRepository.findById(accion.getTurno().getId())
                        .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
                turnoCancelado.setEstado(EstadoTurno.PENDIENTE);
                turnoRepository.save(turnoCancelado);
                colaDeTurnos.agregarTurno(turnoCancelado);
                return "Se deshizo la cancelación del turno";

            default:
                return "Tipo de acción desconocida";
        }
    }
}
