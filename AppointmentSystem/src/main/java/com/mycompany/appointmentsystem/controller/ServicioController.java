/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.controller;

import com.mycompany.appointmentsystem.dto.ServicioDTO;
import com.mycompany.appointmentsystem.dto.ServicioRespuestaDTO;
import com.mycompany.appointmentsystem.service.ServicioService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    // Crear servicio padre (sin padre)
    @PostMapping("/padre")
    public ResponseEntity<ServicioDTO> crearServicioPadre(@RequestBody ServicioDTO servicioDTO) {
        ServicioDTO creado = servicioService.crearServicioPadre(servicioDTO);
        return ResponseEntity.ok(creado);
    }
    
    // Crear servicio hijo, recibe el idPadre por path variable
    @PostMapping("/hijo/{idPadre}")
    public ResponseEntity<ServicioDTO> crearServicioHijo(@RequestBody ServicioDTO servicioDTO,
            @PathVariable Long idPadre) {
        ServicioDTO creado = servicioService.crearServicioHijo(servicioDTO, idPadre);
        return ResponseEntity.ok(creado);
    }
    
    @GetMapping("/arbol/{idPadre}")
    public ResponseEntity<List<ServicioRespuestaDTO>> listarServiciosComoPlano(@PathVariable Long idPadre) {
        List<ServicioRespuestaDTO> servicios = servicioService.listarServiciosEnArbolPlano(idPadre);
        return ResponseEntity.ok(servicios);
    }

    
}
