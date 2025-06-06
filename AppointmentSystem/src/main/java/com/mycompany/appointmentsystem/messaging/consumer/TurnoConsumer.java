/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.messaging.consumer;

import com.mycompany.appointmentsystem.dto.TurnoDTO;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class TurnoConsumer {
    
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${rabbitmq.turno.queue}"),
            exchange = @Exchange(value = "${rabbitmq.turno.exchange}", type = "topic"),
            key = "${rabbitmq.turno.routing-key}")
    )
    public void consumirTurnoCreado(TurnoDTO dto) {
        System.out.println("Turno creado: " + dto);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${rabbitmq.turno.queue}"),
            exchange = @Exchange(value = "${rabbitmq.turno.exchange}", type = "topic"),
            key = "turno.atendido")
    )
    public void consumirTurnoAtendido(TurnoDTO dto) {
        System.out.println("Turno atendido: " + dto);
    }
    
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${rabbitmq.turno.queue}"),
            exchange = @Exchange(value = "${rabbitmq.turno.exchange}", type = "topic"),
            key = "turno.cancelado")
    )
    public void consumirTurnoCancelado(TurnoDTO dto) {
        System.out.println("Turno cancelado: " + dto);
    }

    
}
