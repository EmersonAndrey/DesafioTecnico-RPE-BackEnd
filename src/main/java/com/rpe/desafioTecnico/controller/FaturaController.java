package com.rpe.desafioTecnico.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpe.desafioTecnico.model.dto.FaturaResponseDTO;
import com.rpe.desafioTecnico.service.FaturaService;

@RestController
@RequestMapping("api/faturas")
public class FaturaController {

    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FaturaResponseDTO>> todasFaturasDeUmUsuario(@PathVariable Long id) {
        List<FaturaResponseDTO> response = faturaService.listarFaturasDeUmUsuario(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/pagamento")
    public ResponseEntity<FaturaResponseDTO> registrarPagamento(@PathVariable Long id) {
        FaturaResponseDTO response = faturaService.realizarPagamento(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/atrasadas")
    public ResponseEntity<List<FaturaResponseDTO>> todasFaturasAtrasadas() {
        List<FaturaResponseDTO> response = faturaService.buscarFaturasAtrasadas();
        return ResponseEntity.ok(response);
    }

}
