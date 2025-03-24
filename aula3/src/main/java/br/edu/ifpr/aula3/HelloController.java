package br.edu.ifpr.aula3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exercicio")
public class HelloController {
    
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Olá Mundo!";
    }
}
