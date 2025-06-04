/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.datastructures;

import com.mycompany.appointmentsystem.entity.Turno;
import java.util.LinkedList;
import java.util.Queue;
import org.springframework.stereotype.Component;

@Component
public class ColaDeTurnos {

    private final Queue<Turno> cola = new LinkedList<>();

    public void agregarTurno(Turno turno) {
        cola.offer(turno); // Agrega al final
    }

    public Turno siguienteTurno() {
        return cola.poll(); // Quita y retorna el primero
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public void limpiar() {
        cola.clear();
    }

    public int cantidadPendientes() {
        return cola.size();
    }
    
    public void removerTurno(Turno turno){
        cola.removeIf(t -> t.getId().equals(turno.getId()));
    }

}
