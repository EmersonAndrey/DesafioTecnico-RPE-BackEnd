package com.rpe.desafioTecnico.handler;

public class MensagemErro {

    private String mensagem;
    private int status;

    public MensagemErro(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getStatus() {
        return status;
    }
    
}
