///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.appointmentsystem.service;
//
//
//
//
//import com.mycompany.appointmentsystem.datastructures.historial.GestorHistoriales;
//import com.mycompany.appointmentsystem.datastructures.lista.ListaHistorial;
//import com.mycompany.appointmentsystem.entity.Turno;
//import org.springframework.stereotype.Service;
//
//@Service
//public class HistorialService {
//    private final GestorHistoriales gestor = new GestorHistoriales();
//
//    public void agregarTurnoAlHistorial(Long clienteId, Turno turno) {
//        gestor.agregarTurno(clienteId, turno);
//    }
//
//    public ListaHistorial obtenerHistorial(Long clienteId) {
//        return gestor.obtenerHistorial(clienteId);
//    }
//
//    public boolean existeHistorial(Long clienteId) {
//        return gestor.existeHistorial(clienteId);
//    }
//}
//
