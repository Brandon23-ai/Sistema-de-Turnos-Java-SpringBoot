/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.service;

import com.mycompany.appointmentsystem.datastructures.pila.Accion;
import com.mycompany.appointmentsystem.datastructures.pila.PilaDeAcciones;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccionService {
    
    private final PilaDeAcciones pilaDeAcciones;
    
    public void registrarAccion(Accion accion){
        pilaDeAcciones.agregarAccion(accion.getClienteId(), accion);
    }
    
    public Accion desHacerUltimaAccion(Long clienteId){
        return pilaDeAcciones.deshacerUltimaAccion(clienteId);
    }
}
