/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.appointmentsystem;

import com.mycompany.appointmentsystem.config.RabbitMQServicioProperties;
import com.mycompany.appointmentsystem.config.RabbitMQTurnoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication()
@EnableConfigurationProperties({
    RabbitMQTurnoProperties.class,
    RabbitMQServicioProperties.class
})
public class AppointmentSystem {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentSystem.class, args);
    }
}
