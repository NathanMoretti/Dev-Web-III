package br.edu.ifpr.biblioteca_spring.exceptions;

public class LivroException extends RuntimeException {

    public LivroException(){}
    
    public LivroException(String message){
        super(message);
    }
}
