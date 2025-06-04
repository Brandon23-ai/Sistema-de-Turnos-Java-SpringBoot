/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.repository;


import com.mycompany.appointmentsystem.entity.Turno;
import com.mycompany.appointmentsystem.enums.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByEstadoOrderByFechaCreacionAsc(EstadoTurno estado);
}
