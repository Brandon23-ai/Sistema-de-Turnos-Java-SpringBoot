
package com.mycompany.appointmentsystem.messaging.producer;

import com.mycompany.appointmentsystem.config.RabbitMQServicioProperties;
import com.mycompany.appointmentsystem.dto.ServicioDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class ServicioProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQServicioProperties servicioProperties;

    public void enviarServicioCreado(ServicioDTO servicioDTO) {
        rabbitTemplate.convertAndSend(
                servicioProperties.getExchange(),
                servicioProperties.getRoutingKey(),
                servicioDTO
        );
    }
}
