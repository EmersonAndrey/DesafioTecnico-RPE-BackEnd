package com.rpe.desafioTecnico.model.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.rpe.desafioTecnico.enums.StatusBloqueio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClienteRequestDTO {

    @NotBlank(message = "O nome do cliente deve ser informado")
    private String nome;

    @NotBlank(message = "O CPF do cliente deve ser informado")
    @CPF(message = "O CPF informado não é válido")
    private String cpf;

    @NotNull(message = "A data de nascimento do cliente deve ser informada")
    private LocalDate dataNascimento;

    @NotNull(message = "O status de bloqueio do cliente deve ser informado")
    private StatusBloqueio statusBloqueio;

    @NotNull(message = "O limite de crédito do cliente deve ser informado")
    private Float limiteCredito;

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
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
