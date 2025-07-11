package com.rpe.desafioTecnico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rpe.desafioTecnico.enums.StatusBloqueio;
import com.rpe.desafioTecnico.exception.Cliente.ClienteJaExistenteException;
import com.rpe.desafioTecnico.exception.Cliente.ClienteNaoEncontradoException;
import com.rpe.desafioTecnico.mapper.ClienteMapper;
import com.rpe.desafioTecnico.model.dto.ClienteRequestDTO;
import com.rpe.desafioTecnico.model.dto.ClienteResponseDTO;
import com.rpe.desafioTecnico.model.entities.Cliente;
import com.rpe.desafioTecnico.repository.ClienteRepository;

@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteRequestDTO requestDTO;
    private ClienteResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678900");
        cliente.setStatusBloqueio(StatusBloqueio.A);
        cliente.setLimiteCredito(1000f);

        requestDTO = new ClienteRequestDTO();
        requestDTO.setCpf("12345678900");

        responseDTO = new ClienteResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setCpf("12345678900");
    }

    @Test
    void listarTodosDeveRetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        when(clienteMapper.toDto(cliente)).thenReturn(responseDTO);

        List<ClienteResponseDTO> resultado = clienteService.listarTodos();

        assertEquals(1, resultado.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void salvarClienteComCpfDuplicadoDeveLancarExcecao() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.toDto(cliente)).thenReturn(responseDTO);

        assertThrows(ClienteJaExistenteException.class, () -> clienteService.salvarCliente(requestDTO));
    }

    @Test
    void salvarClienteComSucesso() {
        when(clienteRepository.findAll()).thenReturn(List.of());
        when(clienteMapper.toEntity(requestDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(responseDTO);

        ClienteResponseDTO salvo = clienteService.salvarCliente(requestDTO);

        assertEquals("12345678900", salvo.getCpf());
    }

    @Test
    void buscarPorIdComIdInvalidoDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> clienteService.buscarPorId(0L));
    }

    @Test
    void buscarPorIdComSucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDto(cliente)).thenReturn(responseDTO);

        ClienteResponseDTO encontrado = clienteService.buscarPorId(1L);

        assertEquals("12345678900", encontrado.getCpf());
    }

    @Test
    void buscarPorIdNaoExistenteDeveLancarExcecao() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () -> clienteService.buscarPorId(1L));
    }

    @Test
    void realizarBloqueioClienteComSucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(responseDTO);

        clienteService.realizarBloqueioCliente(1L);

        assertEquals(StatusBloqueio.B, cliente.getStatusBloqueio());
        assertEquals(0f, cliente.getLimiteCredito());
    }

    @Test
    void buscarBloqueadosDeveRetornarSomenteBloqueados() {
        cliente.setStatusBloqueio(StatusBloqueio.B);
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.toDto(cliente)).thenReturn(responseDTO);

        List<ClienteResponseDTO> bloqueados = clienteService.buscarBloqueados();

        assertEquals(1, bloqueados.size());
        assertEquals(StatusBloqueio.B, cliente.getStatusBloqueio());
    }
    
}
