/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.dto;

import com.mycompany.appointmentsystem.enums.EstadoTurno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoDTO {
    private Long id;
    private String nombreCliente;
    private String estado; 
    private Long servicioId; 
    private Long clienteId;
}
