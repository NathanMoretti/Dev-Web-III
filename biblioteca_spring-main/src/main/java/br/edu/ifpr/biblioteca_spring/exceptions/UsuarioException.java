package br.edu.ifpr.biblioteca_spring.exceptions;

public class UsuarioException extends RuntimeException {
    
    public UsuarioException(){}
    
    public UsuarioException(String message){
        super(message);
    }
}


