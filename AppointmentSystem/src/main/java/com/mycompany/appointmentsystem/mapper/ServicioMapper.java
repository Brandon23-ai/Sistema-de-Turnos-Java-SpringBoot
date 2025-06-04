/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.mapper;

import com.mycompany.appointmentsystem.dto.ServicioDTO;
import com.mycompany.appointmentsystem.entity.Servicio;


public class ServicioMapper {
    public static ServicioDTO toDTO(Servicio servicio) {
        if (servicio == null) {
            return null;
        }
        return new ServicioDTO(servicio.getId(), servicio.getNombre(), servicio.getDescripcion());
    }

    public static Servicio toEntity(ServicioDTO ServicioDto) {
        if (ServicioDto == null) {
            return null;
        }
        Servicio servicio = new Servicio();
        servicio.setId(ServicioDto.getId());
        servicio.setNombre(ServicioDto.getNombre());
        servicio.setDescripcion(ServicioDto.getDescripcion());
        return servicio;
    }
    
}
