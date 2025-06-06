/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.service;





import com.mycompany.appointmentsystem.datastructures.ListaHistorial;
import com.mycompany.appointmentsystem.dto.TurnoDTO;
import com.mycompany.appointmentsystem.entity.Turno;
import com.mycompany.appointmentsystem.mapper.TurnoMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class HistorialService {

    private final ListaHistorial listaHistorial;

    public List<TurnoDTO> obtenerHistorialPorCliente(Long clienteId) {
        log.info("Consultando historial de turnos para el cliente con ID: {}", clienteId);

        List<Turno> historial = listaHistorial.obtenerHistorial(clienteId);

        if (historial.isEmpty()) {
            log.warn("No se encontró historial para el cliente con ID: {}", clienteId);
        } else {
            log.info("Historial recuperado con {} turnos para el cliente ID: {}", historial.size(), clienteId);
        }

        return historial.stream()
                .map(TurnoMapper::toDTO)
                .toList();
    }

}

