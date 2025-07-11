package com.rpe.desafioTecnico.exception.Cliente;

public class ClienteJaExistenteException extends RuntimeException {
    
    public ClienteJaExistenteException(String mensagem){
        super(mensagem);
    }
}
