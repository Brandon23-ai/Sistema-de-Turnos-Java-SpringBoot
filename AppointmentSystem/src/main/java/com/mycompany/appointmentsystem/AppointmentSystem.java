/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.appointmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
    "com.mycompany.appointmentsystem"
})
public class AppointmentSystem {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentSystem.class, args);
    }
}
