package com.rpe.desafioTecnico.scheduler;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rpe.desafioTecnico.enums.StatusFatura;
import com.rpe.desafioTecnico.model.entities.Cliente;
import com.rpe.desafioTecnico.model.entities.Fatura;
import com.rpe.desafioTecnico.repository.ClienteRepository;
import com.rpe.desafioTecnico.service.ClienteService;

@SpringBootTest
public class ClienteSchedulerTest {
    
   @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteScheduler clienteScheduler;

    private Cliente cliente;
    private Fatura faturaAtrasada;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);

        faturaAtrasada = new Fatura();
        faturaAtrasada.setDataPagamento(null);
        faturaAtrasada.setDataVencimento(LocalDate.now().minusDays(5));
        faturaAtrasada.setStatusFatura(StatusFatura.A);

        cliente.setFaturas(List.of(faturaAtrasada));
    }

    @Test
    void deveBloquearClienteComFaturaAtrasada() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        clienteScheduler.verificarBloqueioParaTodosClientes();

        verify(clienteService).realizarBloqueioCliente(1L);
    }

    @Test
    void naoDeveBloquearClienteSemFaturaAtrasada() {
        faturaAtrasada.setDataVencimento(LocalDate.now().plusDays(1));
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        clienteScheduler.verificarBloqueioParaTodosClientes();

        verify(clienteService, never()).realizarBloqueioCliente(anyLong());
    }

    @Test
    void naoDeveBloquearClienteSemFaturas() {
        Cliente clienteSemFaturas = new Cliente();
        clienteSemFaturas.setId(2L);
        clienteSemFaturas.setFaturas(List.of());

        when(clienteRepository.findAll()).thenReturn(List.of(clienteSemFaturas));

        clienteScheduler.verificarBloqueioParaTodosClientes();

        verify(clienteService, never()).realizarBloqueioCliente(anyLong());
    }

}
