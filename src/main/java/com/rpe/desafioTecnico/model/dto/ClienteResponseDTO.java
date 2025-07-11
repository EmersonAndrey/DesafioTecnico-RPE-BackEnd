package com.rpe.desafioTecnico.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.rpe.desafioTecnico.enums.StatusBloqueio;

public class ClienteResponseDTO {

    private Long id;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private StatusBloqueio statusBloqueio;
    private Float limiteCredito;
    private List<FaturaResponseDTO> faturas;

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<FaturaResponseDTO> getFaturas() {
        return faturas;
    }

    public Long getId() {
        return id;
    }

    public Float getLimiteCredito() {
        return limiteCredito;
    }

    public String getNome() {
        return nome;
    }

    public StatusBloqueio getStatusBloqueio() {
        return statusBloqueio;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setFaturas(List<FaturaResponseDTO> faturas) {
        this.faturas = faturas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLimiteCredito(Float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatusBloqueio(StatusBloqueio statusBloqueio) {
        this.statusBloqueio = statusBloqueio;
    }

}
