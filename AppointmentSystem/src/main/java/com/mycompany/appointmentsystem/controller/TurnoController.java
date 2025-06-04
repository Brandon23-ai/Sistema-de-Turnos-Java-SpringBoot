


package com.mycompany.appointmentsystem.controller;


import com.mycompany.appointmentsystem.dto.TurnoDTO;
import com.mycompany.appointmentsystem.service.TurnoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    
    private final TurnoService turnoService;
    
    //Solicitar un turno
    @PostMapping
    public ResponseEntity<TurnoDTO> solicitarTurno (@RequestBody TurnoDTO dto){
        TurnoDTO creado = turnoService.solicitarTurno(dto);
        return ResponseEntity.ok(creado);
        
    }
    
    //Atender siguiente turno
    @GetMapping("/atender")
    public ResponseEntity<TurnoDTO> atenderSiguienteTurno(){
        TurnoDTO atendido = turnoService.atenderSiguienteTurno();
        return ResponseEntity.ok(atendido);
    }
    
    //Cancelar turno por id
    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoDTO> cancelarTurno(@PathVariable Long id){
        TurnoDTO cancelado = turnoService.cancelarTurno(id);
        return ResponseEntity.ok(cancelado);
    }
}
