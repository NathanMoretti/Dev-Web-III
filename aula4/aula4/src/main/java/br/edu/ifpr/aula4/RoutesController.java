package br.edu.ifpr.aula4;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.micrometer.common.lang.Nullable;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/routes")
public class RoutesController {
    
    @GetMapping("/data")
    @ResponseBody
    public String obtainData(){
        return "return some data";
    }


    @PostMapping("/create")
    @ResponseBody
    public String create(){
        return "CREATE";
    } 

    @GetMapping("/bebidas/{drinkType}")
    @ResponseBody
    public String drinkType(@PathVariable (value = "drinkType", required = false ) @Nullable String drinkType){
        return "The drink chosen was: " + drinkType;
    }

    @GetMapping("/bebidas")
    @ResponseBody
    public String drinkType(){
        return "All drinks";
    }


    @GetMapping("/variables")
    @ResponseBody
    public String getURLVariable(@RequestParam Optional<String> category) {
        //if (category != null){
        return "The category chosen was: " + category.orElse("is null");
        //}
        //return "The vategory chosen was null";
    }


    @PostMapping("/login")
    @ResponseBody
    public String getForm(@RequestParam String user, @RequestParam String password) {
        return user + password;
       
    }


}
