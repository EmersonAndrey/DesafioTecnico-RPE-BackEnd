package com.rpe.desafioTecnico.exception.Fatura;

public class FaturaNaoEncontradaException extends RuntimeException{
    
    public FaturaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

}