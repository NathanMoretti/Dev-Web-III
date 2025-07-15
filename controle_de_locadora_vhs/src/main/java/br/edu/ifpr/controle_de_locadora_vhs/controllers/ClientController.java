package br.edu.ifpr.controle_de_locadora_vhs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.controle_de_locadora_vhs.entities.Client;
import br.edu.ifpr.controle_de_locadora_vhs.services.ClientService;

@Controller
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("mensagem", "E-mail ou senha inválidos.");
        }
        if (logout != null) {
            model.addAttribute("mensagem", "Logout realizado com sucesso.");
        }
        return "login";
    }

    @GetMapping("/cadastro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processRegistration(@ModelAttribute Client client,
                                      RedirectAttributes redirectAttributes) {
        if (clientService.existsByEmail(client.getEmail())) {
            redirectAttributes.addFlashAttribute("mensagem", "Este e-mail já está cadastrado.");
            return "redirect:/cadastro";
        }

        clientService.save(client);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso! Faça login.");
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Client client = clientService.findByEmail(currentPrincipalName);

        if (client != null) {
            model.addAttribute("usuario", client);
        } else {
            model.addAttribute("usuario", new Client());
            ((Client) model.getAttribute("usuario")).setName("Usuário");
        }
        return "home";
    }
}