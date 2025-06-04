/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.mapper;

import com.mycompany.appointmentsystem.enums.EstadoTurno;
import com.mycompany.appointmentsystem.dto.TurnoDTO;
import com.mycompany.appointmentsystem.entity.Cliente;
import com.mycompany.appointmentsystem.entity.Servicio;
import com.mycompany.appointmentsystem.entity.Turno;


public class TurnoMapper {

    public static TurnoDTO toDTO(Turno turno) {
        if (turno == null) {
            return null;
        }

        return TurnoDTO.builder()
                .id(turno.getId())
                .nombreCliente(turno.getNombreCliente())
                .estado(turno.getEstado().name())
                .servicioId(turno.getServicio().getId())
                .clienteId(turno.getCliente().getId())
                .build();
    }

    public static Turno toEntity(TurnoDTO dto, Servicio servicio, Cliente cliente) {
        if (dto == null) {
            return null;
        }

        return Turno.builder()
                .nombreCliente(dto.getNombreCliente())
                .estado(EstadoTurno.PENDIENTE)
                .fechaCreacion(java.time.LocalDateTime.now())
                .servicio(servicio)
                .cliente(cliente) 
                .build();
    }

}
