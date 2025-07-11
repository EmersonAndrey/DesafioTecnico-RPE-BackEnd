package com.rpe.desafioTecnico.model.entities;

import java.time.LocalDate;
import java.util.List;

import com.rpe.desafioTecnico.enums.StatusBloqueio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_bloqueio", nullable = false)
    private StatusBloqueio statusBloqueio;

    @Column(name = "limite_credito", nullable = false)
    private Float limiteCredito;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Fatura> faturas;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public Long getId() {
        return id;
    }

    public Float getLimiteCredito() {
        return limiteCredito;
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

    public void setFaturas(List<Fatura> faturas) {
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
