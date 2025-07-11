package com.rpe.desafioTecnico.enums;

public enum StatusBloqueio {
    
    A("Ativo"),
    B("Bloqueado");

    private final String descricao;

    StatusBloqueio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
