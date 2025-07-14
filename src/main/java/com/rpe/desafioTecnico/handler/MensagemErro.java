package com.rpe.desafioTecnico.handler;

public class MensagemErro {

    private String message;
    private int status;

    public MensagemErro(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
    
}
