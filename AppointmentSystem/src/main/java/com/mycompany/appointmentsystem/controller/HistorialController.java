/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.controller;

import com.mycompany.appointmentsystem.dto.TurnoDTO;
import com.mycompany.appointmentsystem.service.HistorialService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/historial")
public class HistorialController {
    
    private final HistorialService historialService;
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<TurnoDTO>> obtenerHistorial(@PathVariable Long clienteId){
        List<TurnoDTO> historial = historialService.obtenerHistorialPorCliente(clienteId);
        return ResponseEntity.ok(historial);
    }  
}
