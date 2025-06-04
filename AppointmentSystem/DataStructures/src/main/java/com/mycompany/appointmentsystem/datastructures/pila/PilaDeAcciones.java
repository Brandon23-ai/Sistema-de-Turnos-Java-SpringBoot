/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.datastructures.pila;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.springframework.stereotype.Component;

@Component
public class PilaDeAcciones {
    
    //Una pila por cliente
    private Map<Long, Stack<Accion>> pilaPorCliente = new HashMap<>();
    
    //Agregar una ccion a la pila
    public void agregarAccion(Long clienteId, Accion accion){
        pilaPorCliente
                .computeIfAbsent(clienteId, k-> new Stack<>())
                .push(accion);        
    }
    
    //Deshacer accion en la pila
    public Accion deshacerUltimaAccion(Long clienteId){
        Stack<Accion> pila = pilaPorCliente.get(clienteId);
        if (pila != null && !pila.isEmpty()) {
            return pila.pop();
        }
        return null;
    }
    
}
