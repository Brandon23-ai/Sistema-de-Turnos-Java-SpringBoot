/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.messaging.consumer;

import com.mycompany.appointmentsystem.dto.ServicioDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ServicioConsumer {

    @RabbitListener(queues = "#{rabbitMQProperties.queue}")
    public void recibirServicio(ServicioDTO servicioDTO) {
        System.out.println("Servicio recibido desde RabbitMQ:");
        System.out.println("ID: " + servicioDTO.getId());
        System.out.println("Nombre: " + servicioDTO.getNombre());
        System.out.println("Descripción: " + servicioDTO.getDescripcion());
    }
    
}
