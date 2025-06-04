
package com.mycompany.appointmentsystem.messaging.producer;

import com.mycompany.appointmentsystem.config.RabbitMQProperties;
import com.mycompany.appointmentsystem.dto.ServicioDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ServicioProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    public ServicioProducer(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    public void enviarServicioCreado(ServicioDTO servicioDTO) {
        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getExchange(),
                rabbitMQProperties.getRoutingKey(),
                servicioDTO
        );
    }
}
