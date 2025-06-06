/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class RabbitMQConfig {
    
    @Qualifier("rabbitMQTurnoProperties")
    private final RabbitMQTurnoProperties turnoProps;

    @Qualifier("rabbitMQServicioProperties")
    private final RabbitMQServicioProperties servicioProps;

    
    //Configg para turnos
    @Bean
    public Queue turnoQueue() {
        return new Queue(turnoProps.getQueue());
    }

    @Bean
    public TopicExchange turnoExchange() {
        return new TopicExchange(turnoProps.getExchange());
    }

    @Bean
    public Binding turnoBinding(Queue turnoQueue, TopicExchange turnoExchange) {
        return BindingBuilder.bind(turnoQueue).to(turnoExchange).with(turnoProps.getRoutingKey());
    }
    
    //Config para servicios
    @Bean
    public Queue servicioQueue() {
        return new Queue(servicioProps.getQueue());
    }

    @Bean
    public TopicExchange servicioExchange() {
        return new TopicExchange(servicioProps.getExchange());
    }

    @Bean
    public Binding servicioBinding(Queue servicioQueue, TopicExchange servicioExchange) {
        return BindingBuilder.bind(servicioQueue).to(servicioExchange).with(servicioProps.getRoutingKey());
    }
    
    //Converter y rabbitTemplate
    @Bean
    public Jackson2JsonMessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}