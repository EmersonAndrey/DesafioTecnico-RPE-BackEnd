package com.rpe.desafioTecnico.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpe.desafioTecnico.model.dto.ClienteRequestDTO;
import com.rpe.desafioTecnico.model.dto.ClienteResponseDTO;
import com.rpe.desafioTecnico.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> buscarTodos() {
        List<ClienteResponseDTO> response = clienteService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO request) {
        ClienteResponseDTO response = clienteService.salvarCliente(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        ClienteResponseDTO response = clienteService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id) {
        ClienteResponseDTO response = clienteService.realizarBloqueioCliente(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bloqueados")
    public ResponseEntity<List<ClienteResponseDTO>> buscarBloqueados() {
        List<ClienteResponseDTO> response = clienteService.buscarBloqueados();
        return ResponseEntity.ok(response);
    }

}
