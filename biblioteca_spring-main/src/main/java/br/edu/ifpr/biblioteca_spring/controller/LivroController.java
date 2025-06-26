
package br.edu.ifpr.biblioteca_spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.biblioteca_spring.models.Livro;
import br.edu.ifpr.biblioteca_spring.service.LivroService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", livroService.listarTodos());
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String formularioNovoLivro(Model model) {
        model.addAttribute("livro", new Livro(null, "", ""));
        return "livros/form";
    }

    @PostMapping
    public String salvar(@Valid Livro livro, BindingResult fields, Model model, RedirectAttributes redirectAttributes) throws IOException {
        if(fields.hasErrors()){
            return "livros/form";
        }

        livroService.adicionar(livro.getTitulo(), livro.getAutor());
        redirectAttributes.addFlashAttribute("message", "sucesso");
        return "redirect:/livros";
    }
}
