/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.messaging.producer;

import com.mycompany.appointmentsystem.config.RabbitMQTurnoProperties;
import com.mycompany.appointmentsystem.dto.TurnoDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TurnoProducer {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQTurnoProperties turnoProperties;
    
    public void enviarTurnoCreado(TurnoDTO dto) {
        rabbitTemplate.convertAndSend(turnoProperties.getExchange(), "turno.creado", dto);
    }

    public void enviarTurnoAtendido(TurnoDTO dto) {
        rabbitTemplate.convertAndSend(turnoProperties.getExchange(), "turno.atendido", dto);
    }

    public void enviarTurnoCancelado(TurnoDTO dto) {
        rabbitTemplate.convertAndSend(turnoProperties.getExchange(), "turno.cancelado", dto);
    }

}
