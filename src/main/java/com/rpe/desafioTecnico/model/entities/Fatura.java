package com.rpe.desafioTecnico.model.entities;

import java.time.LocalDate;

import com.rpe.desafioTecnico.enums.StatusFatura;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(nullable = false)
    private float valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusFatura statusFatura;

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public Long getId() {
        return id;
    }

    public StatusFatura getStatusFatura() {
        return statusFatura;
    }

    public float getValor() {
        return valor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void setStatusFatura(StatusFatura statusFatura) {
        this.statusFatura = statusFatura;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
