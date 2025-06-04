/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.messaging.consumer;

import com.mycompany.appointmentsystem.dto.TurnoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class TurnoConsumer {
    
    @RabbitListener(queues = "#{rabbitMQProperties.queue}")
    public void recibirTurno(TurnoDTO dto){
        System.out.println("Turno recibido desde RabbitMQ: ");
        System.out.println("ID: " + dto.getId());
        System.out.println("Nombre Cliente: "+ dto.getNombreCliente());
        System.out.println("Estado: " + dto.getEstado());
        System.out.println("ServicioId: " + dto.getServicioId());
        System.out.println("ClienteId: "+ dto.getClienteId());
    }
    
}
