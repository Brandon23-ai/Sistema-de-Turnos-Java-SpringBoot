/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.datastructures;

import com.mycompany.appointmentsystem.entity.Servicio;
import java.util.*;

public class ArbolServicios {

    private final Map<Long, List<Servicio>> hijosPorPadre = new HashMap<>();
    private final Map<Long, Servicio> serviciosPorId = new HashMap<>();
    private final List<Servicio> raices = new ArrayList<>();

    //Insertar un servicio padre (sin padre)
    public void agregarServicioPadre(Servicio servicio) {
        if (servicio.getPadre() != null) {
            throw new IllegalArgumentException("Este servicio tiene padre, no es raíz.");
        }
        serviciosPorId.put(servicio.getId(), servicio);
        raices.add(servicio);
    }

    //Insertar un servicio hijo a un padre dado
    public void agregarServicioHijo(Long idPadre, Servicio hijo) {
        Servicio padre = serviciosPorId.get(idPadre);
        if (padre == null) {
            throw new IllegalArgumentException("Padre no encontrado con id: " + idPadre);
        }
        hijo.setPadre(padre);
        serviciosPorId.put(hijo.getId(), hijo);
        hijosPorPadre.computeIfAbsent(idPadre, k -> new ArrayList<>()).add(hijo);
    }

    //Obtener hijos de un servicio padre
    public List<Servicio> obtenerHijosDe(Long idPadre) {
        return hijosPorPadre.getOrDefault(idPadre, Collections.emptyList());
    }

    //Obtener todos los servicios raíz
    public List<Servicio> obtenerRaices() {
        return raices;
    }

    //Obtener todos los servicios registrados (útil para recorrer)
    public Collection<Servicio> obtenerTodos() {
        return serviciosPorId.values();
    }

}
