package br.edu.ifpr.aula4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping ("/produtos")
@Controller
public class exercicio2{
    
    @GetMapping("/search")
    @ResponseBody
    public String busca (@RequestParam String nome, @RequestParam String seila){
        return nome + " " + seila;
    }

    @GetMapping("/desc/{id}")
    @ResponseBody
    public String id(@RequestParam String campo) {
        if (campo != null){
        return "Lorem usupum";
        }
        return "All campos";
    }
    
}
