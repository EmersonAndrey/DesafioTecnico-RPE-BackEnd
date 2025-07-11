package com.rpe.desafioTecnico.scheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rpe.desafioTecnico.model.entities.Cliente;
import com.rpe.desafioTecnico.repository.ClienteRepository;
import com.rpe.desafioTecnico.service.ClienteService;

@Service
public class ClienteScheduler {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    public ClienteScheduler(ClienteRepository clienteRepository, ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    /**
     * Executa todo dia às 12:00 (meio-dia)
     */
    @Scheduled(cron = "0 0 12 * * *")
    @Transactional
    public void verificarBloqueioParaTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll().stream()
                .filter(c -> !c.getFaturas().isEmpty())
                .collect(Collectors.toList());

        for (Cliente cliente : clientes) {
            boolean temAtrasada = cliente.getFaturas().stream()
                    .anyMatch(f -> f.getDataPagamento() == null &&
                            f.getDataVencimento().plusDays(3).isBefore(LocalDate.now()));

            if (temAtrasada) {
                clienteService.realizarBloqueioCliente(cliente.getId());
            }
        }
    }
}
