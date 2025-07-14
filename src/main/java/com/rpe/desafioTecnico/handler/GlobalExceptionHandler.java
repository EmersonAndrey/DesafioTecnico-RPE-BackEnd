package com.rpe.desafioTecnico.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rpe.desafioTecnico.exception.Cliente.ClienteJaExistenteException;
import com.rpe.desafioTecnico.exception.Cliente.ClienteNaoEncontradoException;
import com.rpe.desafioTecnico.exception.Fatura.FaturaNaoEncontradaException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MensagemErro(errors.get(0), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<MensagemErro> handlerClienteNaoEncontrado(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensagemErro(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(ClienteJaExistenteException.class)
    public ResponseEntity<MensagemErro> handlerClienteJaaExistente(ClienteJaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensagemErro(ex.getMessage(), HttpStatus.CONFLICT.value()));
    }

     @ExceptionHandler(FaturaNaoEncontradaException.class)
    public ResponseEntity<MensagemErro> handlerFaturaNaoEncontrada(FaturaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensagemErro(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
    
}
