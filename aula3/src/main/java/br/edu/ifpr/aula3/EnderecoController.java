package br.edu.ifpr.aula3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping
@Controller
public class EnderecoController {
    

    @GetMapping("/requisicao")
    @ResponseBody
    public String hello(HttpServletRequest request){
        String metodo = request.getMethod();
        String URI = request.getRequestURI();
        String protocolo = request.getProtocol();

        String resposta = "Método: " + metodo + "URI: " + URI + "Protocolo: " + protocolo;
        return resposta;
    }

    @GetMapping("/cabecalhos")
    @ResponseBody
    public String cabecalhos(HttpServletRequest request){
        String host = request.getHeader("Host");
        String userAgent = request.getHeader("User-Agent");
        String accept = request.getHeader("Accept-enconding");
        String language = request.getHeader("Accept-language");

        return "Host: " + host + "User-agent" + userAgent + "Accept: " + accept + "Language: " + language;   
     }
}
