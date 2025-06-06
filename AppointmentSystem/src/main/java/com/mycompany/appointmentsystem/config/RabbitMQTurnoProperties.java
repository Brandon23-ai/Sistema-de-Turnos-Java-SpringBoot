
package com.mycompany.appointmentsystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "rabbitmq.turno")
public class RabbitMQTurnoProperties {
    private String queue;
    private String exchange;
    private String routingKey;  
}
