package com.rpe.desafioTecnico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rpe.desafioTecnico.enums.StatusBloqueio;
import com.rpe.desafioTecnico.exception.Cliente.ClienteJaExistenteException;
import com.rpe.desafioTecnico.exception.Cliente.ClienteNaoEncontradoException;
import com.rpe.desafioTecnico.mapper.ClienteMapper;
import com.rpe.desafioTecnico.model.dto.ClienteRequestDTO;
import com.rpe.desafioTecnico.model.dto.ClienteResponseDTO;
import com.rpe.desafioTecnico.model.entities.Cliente;
import com.rpe.desafioTecnico.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteResponseDTO salvarCliente(ClienteRequestDTO request) {

        Optional<ClienteResponseDTO> clienteEncontrado = listarTodos()
                .stream().filter(c -> c.getCpf().equals(request.getCpf()))
                .findFirst();

        if (clienteEncontrado.isPresent()) {
            throw new ClienteJaExistenteException("Atualmente, já existe um cliente com esse CPF");
        }

        Cliente entity = clienteMapper.toEntity(request);
        Cliente clienteSalvo = clienteRepository.save(entity);

        return clienteMapper.toDto(clienteSalvo);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID não pode ser nulo ou negativo.");
        }

        Cliente clienteEncontrado = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com id" + id + " não foi encontrado"));

        return clienteMapper.toDto(clienteEncontrado);
    }

    @Transactional
    public ClienteResponseDTO realizarBloqueioCliente(Long id) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com id" + id + " não foi encontrado"));

        clienteExistente.setStatusBloqueio(StatusBloqueio.B);
        clienteExistente.setLimiteCredito(0f);

        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);

        return clienteMapper.toDto(clienteAtualizado);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> buscarBloqueados() {
        return clienteRepository.findAll().stream()
                .filter(c -> c.getStatusBloqueio() == StatusBloqueio.B)
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

}
