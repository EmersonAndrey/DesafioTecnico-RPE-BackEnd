package com.rpe.desafioTecnico.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rpe.desafioTecnico.enums.StatusBloqueio;
import com.rpe.desafioTecnico.enums.StatusFatura;
import com.rpe.desafioTecnico.exception.Fatura.FaturaNaoEncontradaException;
import com.rpe.desafioTecnico.mapper.FaturaMapper;
import com.rpe.desafioTecnico.model.dto.ClienteResponseDTO;
import com.rpe.desafioTecnico.model.dto.FaturaResponseDTO;
import com.rpe.desafioTecnico.model.entities.Fatura;
import com.rpe.desafioTecnico.repository.FaturaRepository;

@Service
public class FaturaService {

    private final FaturaRepository faturaRepository;
    private final ClienteService clienteService;
    private final FaturaMapper faturaMapper;

    public FaturaService(FaturaRepository faturaRepository, ClienteService clienteService, FaturaMapper faturaMapper) {
        this.faturaRepository = faturaRepository;
        this.clienteService = clienteService;
        this.faturaMapper = faturaMapper;

    }

    @Transactional(readOnly = true)
    public List<FaturaResponseDTO> listarFaturasDeUmUsuario(Long id) {
        ClienteResponseDTO cliente = clienteService.buscarPorId(id);
        return cliente.getFaturas();
    }

    @Transactional
    public FaturaResponseDTO realizarPagamento(Long id) {
        Fatura fatura = buscarPorId(id);

        fatura.setStatusFatura(StatusFatura.P);
        fatura.setDataPagamento(LocalDate.now());
        fatura.getCliente().setStatusBloqueio(StatusBloqueio.A);

        Fatura faturaPaga = faturaRepository.save(fatura);

        return faturaMapper.toDto(faturaPaga);
    }

    @Transactional
    public List<FaturaResponseDTO> buscarFaturasAtrasadas() {
        return faturaRepository.findAll().stream()
                .filter(f -> f.getStatusFatura() == StatusFatura.A)
                .map(faturaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private Fatura buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID não pode ser nulo ou negativo.");
        }

        Fatura faturaEncontrada = faturaRepository.findById(id)
                .orElseThrow(() -> new FaturaNaoEncontradaException("A fatura com id" + id + " não foi encontrada"));

        return faturaEncontrada;
    }

}
