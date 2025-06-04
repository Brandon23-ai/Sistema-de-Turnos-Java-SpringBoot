/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appointmentsystem.mapper;

import com.mycompany.appointmentsystem.dto.ClienteDTO;
import com.mycompany.appointmentsystem.entity.Cliente;

/**
 *
 * @author btmor
 */
public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(cliente.getId(), 
                cliente.getNombre(), 
                cliente.getDpi());
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNombre(dto.getNombre());
        cliente.setDpi(dto.getDpi());
        return cliente;
    }

}
