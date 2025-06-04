/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.datastructures;

import com.mycompany.appointmentsystem.entity.Turno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ListaHistorial {

    private Map<Long, List<Turno>> historialPorCliente = new HashMap<>();

    public void agregarTurno(Long clienteId, Turno turno) {
        historialPorCliente
                .computeIfAbsent(clienteId, k -> new ArrayList<>())
                .add(turno);
    }

    public List<Turno> obtenerHistorial(Long clienteId) {
        return historialPorCliente.getOrDefault(clienteId, new ArrayList<>());
    }

}
