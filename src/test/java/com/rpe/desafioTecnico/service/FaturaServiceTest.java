package com.rpe.desafioTecnico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rpe.desafioTecnico.enums.StatusBloqueio;
import com.rpe.desafioTecnico.enums.StatusFatura;
import com.rpe.desafioTecnico.exception.Fatura.FaturaNaoEncontradaException;
import com.rpe.desafioTecnico.mapper.FaturaMapper;
import com.rpe.desafioTecnico.model.dto.ClienteResponseDTO;
import com.rpe.desafioTecnico.model.dto.FaturaResponseDTO;
import com.rpe.desafioTecnico.model.entities.Cliente;
import com.rpe.desafioTecnico.model.entities.Fatura;
import com.rpe.desafioTecnico.repository.FaturaRepository;

@SpringBootTest
public class FaturaServiceTest {

    @Mock
    private FaturaRepository faturaRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private FaturaMapper faturaMapper;

    @InjectMocks
    private FaturaService faturaService;

    private Fatura fatura;
    private FaturaResponseDTO faturaResponseDTO;

    @BeforeEach
    void setUp() {
        fatura = new Fatura();
        fatura.setId(1L);
        fatura.setStatusFatura(StatusFatura.A);
        fatura.setDataPagamento(null);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setStatusBloqueio(StatusBloqueio.B); 

        fatura.setCliente(cliente);

        faturaResponseDTO = new FaturaResponseDTO();
        faturaResponseDTO.setId(1L);
    }

    @Test
    void listarFaturasDeUmUsuarioDeveRetornarLista() {
        ClienteResponseDTO clienteDTO = new ClienteResponseDTO();
        clienteDTO.setFaturas(List.of(faturaResponseDTO));

        when(clienteService.buscarPorId(1L)).thenReturn(clienteDTO);

        List<FaturaResponseDTO> resultado = faturaService.listarFaturasDeUmUsuario(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }

    @Test
    void realizarPagamentoComSucesso() {
        when(faturaRepository.findById(1L)).thenReturn(Optional.of(fatura));
        when(faturaRepository.save(fatura)).thenReturn(fatura);
        when(faturaMapper.toDto(fatura)).thenReturn(faturaResponseDTO);

        FaturaResponseDTO resultado = faturaService.realizarPagamento(1L);

        assertEquals(StatusFatura.P, fatura.getStatusFatura());
        assertNotNull(fatura.getDataPagamento());
        assertEquals(1L, resultado.getId());
    }

    @Test
    void buscarFaturasAtrasadasDeveRetornarListaDeAtrasadas() {
        Fatura fatura2 = new Fatura();
        fatura2.setId(2L);
        fatura2.setStatusFatura(StatusFatura.A);

        when(faturaRepository.findAll()).thenReturn(List.of(fatura, fatura2));
        when(faturaMapper.toDto(fatura)).thenReturn(faturaResponseDTO);
        FaturaResponseDTO faturaResponse2 = new FaturaResponseDTO();
        faturaResponse2.setId(2L);
        when(faturaMapper.toDto(fatura2)).thenReturn(faturaResponse2);

        List<FaturaResponseDTO> atrasadas = faturaService.buscarFaturasAtrasadas();

        assertEquals(2, atrasadas.size());
    }

    @Test
    void buscarPorIdComIdInvalidoDeveLancarExcecao() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> faturaService.realizarPagamento(0L));
    }

    @Test
    void realizarPagamentoFaturaInexistenteDeveLancarExcecao() {
        when(faturaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(FaturaNaoEncontradaException.class, () -> faturaService.realizarPagamento(999L));
    }

}