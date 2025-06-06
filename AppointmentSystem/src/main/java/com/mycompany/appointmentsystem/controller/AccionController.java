/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.controller;

import com.mycompany.appointmentsystem.datastructures.pila.Accion;
import com.mycompany.appointmentsystem.service.AccionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/acciones")
public class AccionController {
    
    private final AccionService accionService;
    
    @PostMapping("/deshacer/cliente/{clienteId}")
    public ResponseEntity<String> deshacerUltimaAccion(@PathVariable Long clienteId) {
        String resultado = accionService.desHacerUltimaAccion(clienteId);
        if (resultado.startsWith("Se deshizo")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }
    

    
}
