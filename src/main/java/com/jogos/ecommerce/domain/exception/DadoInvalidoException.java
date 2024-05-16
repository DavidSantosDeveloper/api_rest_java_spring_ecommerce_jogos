package com.jogos.ecommerce.domain.exception;

public class DadoInvalidoException extends RuntimeException{
    public DadoInvalidoException(String message){
        super(message);
    }
}
