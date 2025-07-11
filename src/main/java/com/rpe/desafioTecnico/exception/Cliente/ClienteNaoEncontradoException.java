package com.rpe.desafioTecnico.exception.Cliente;

public class ClienteNaoEncontradoException extends RuntimeException{
    
    public ClienteNaoEncontradoException(String mensagem){
        super(mensagem);
    }

}