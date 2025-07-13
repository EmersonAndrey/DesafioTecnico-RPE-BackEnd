package com.rpe.desafioTecnico.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rpe.desafioTecnico.enums.StatusFatura;

import jakarta.validation.constraints.NotNull;

public class FaturaRequestDTO {

    @NotNull(message = "O cliente da fatura deve ser informado")
    private Long cliente_id;

    @NotNull(message = "A data de vencimento da fatura deve ser informada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataVencimento;

    @NotNull(message = "O valor da fatura deve ser informado")
    private float valor;

    @NotNull(message = "O status da fatura deve ser informado")
    private StatusFatura statusFatura;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataPagamento;

    public Long getCliente_id() {
        return cliente_id;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public StatusFatura getStatusFatura() {
        return statusFatura;
    }

    public float getValor() {
        return valor;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setStatusFatura(StatusFatura statusFatura) {
        this.statusFatura = statusFatura;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
