/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.repository;


import com.mycompany.appointmentsystem.entity.Turno;
import com.mycompany.appointmentsystem.enums.EstadoTurno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByEstadoOrderByFechaCreacionAsc(EstadoTurno estado);
}
