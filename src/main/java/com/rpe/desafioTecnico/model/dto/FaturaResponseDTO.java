package com.rpe.desafioTecnico.model.dto;

import java.time.LocalDate;

import com.rpe.desafioTecnico.enums.StatusFatura;

public class FaturaResponseDTO {

    private Long id;
    private String nomeCliente;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private float valor;
    private StatusFatura statusFatura;

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public StatusFatura getStatusFatura() {
        return statusFatura;
    }

    public float getValor() {
        return valor;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setStatusFatura(StatusFatura statusFatura) {
        this.statusFatura = statusFatura;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
