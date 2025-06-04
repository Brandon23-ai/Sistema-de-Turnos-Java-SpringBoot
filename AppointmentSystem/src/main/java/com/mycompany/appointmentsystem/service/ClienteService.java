package com.mycompany.appointmentsystem.service;

import com.mycompany.appointmentsystem.dto.ClienteDTO;
import com.mycompany.appointmentsystem.entity.Cliente;
import com.mycompany.appointmentsystem.mapper.ClienteMapper;
import com.mycompany.appointmentsystem.repository.ClienteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO registrarCliente(ClienteDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(guardado);
    }

    public ClienteDTO obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ClienteMapper::toDTO).orElse(null);
    }

    public boolean eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
