/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.service;





import com.mycompany.appointmentsystem.datastructures.ListaHistorial;
import com.mycompany.appointmentsystem.dto.TurnoDTO;
import com.mycompany.appointmentsystem.entity.Turno;
import com.mycompany.appointmentsystem.mapper.TurnoMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HistorialService {
    
    private final ListaHistorial listaHistorial;
    
    public List<TurnoDTO> obtenerHistorialPorCliente(Long clienteId){
        List<Turno> historial = listaHistorial.obtenerHistorial(clienteId);
        return historial.stream()
                .map(TurnoMapper::toDTO)
                .toList();
    }
    
}

