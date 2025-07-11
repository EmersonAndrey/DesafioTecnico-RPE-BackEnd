package com.rpe.desafioTecnico.enums;

public enum StatusFatura {
    
    P("Paga"),
    A("Atrasada"),
    B("Aberta");

    private final String descricao;

    StatusFatura(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
