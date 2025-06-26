package br.edu.ifpr.biblioteca_spring.controller.errors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.ifpr.biblioteca_spring.exceptions.UsuarioException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UsuarioException.class)
    public String validacaoEstudanteException(UsuarioException ex, Model model){


        model.addAttribute("erro", "Um problema ocorreu");
        model.addAttribute("descricao", ex.getMessage());

        return "error/error";

    }

    @ExceptionHandler(Exception.class)
    public String validacaoGenericaException(Exception ex, Model model){

        model.addAttribute("erro", "Um problema ocorreu");
        model.addAttribute("descricao", ex.getMessage());

        return "error/error";
    }
}
