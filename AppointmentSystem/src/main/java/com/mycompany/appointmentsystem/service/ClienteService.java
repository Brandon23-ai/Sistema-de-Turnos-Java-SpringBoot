package com.mycompany.appointmentsystem.service;

import com.mycompany.appointmentsystem.dto.ClienteDTO;
import com.mycompany.appointmentsystem.entity.Cliente;
import com.mycompany.appointmentsystem.mapper.ClienteMapper;
import com.mycompany.appointmentsystem.repository.ClienteRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO registrarCliente(ClienteDTO dto) {
        log.info("Registrando nuevo cliente: {}", dto.getNombre());
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        log.info("Cliente registrado con ID: {}", guardado.getId());
        return ClienteMapper.toDTO(guardado);
    }

    public ClienteDTO obtenerClientePorId(Long id) {
        log.info("Buscando cliente con ID: {}", id);
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            log.info("Cliente encontrado: {}", cliente.get().getNombre());
            return ClienteMapper.toDTO(cliente.get());
        } else {
            log.warn("Cliente no encontrado con ID: {}", id);
            return null;
        }
    }

    public boolean eliminarCliente(Long id) {
        log.info("Intentando eliminar cliente con ID: {}", id);

        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            log.info("Cliente eliminado con éxito: ID {}", id);
            return true;
        } else {
            log.warn("No se encontró cliente para eliminar con ID: {}", id);
            return false;
        }
    }
}
