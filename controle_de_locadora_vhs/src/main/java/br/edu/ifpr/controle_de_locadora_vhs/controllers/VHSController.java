package br.edu.ifpr.controle_de_locadora_vhs.controllers;

import java.time.LocalDate; // Importar LocalDate
import java.util.List;
import java.util.Optional; // Importar Optional

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; // Importar PathVariable
import org.springframework.web.bind.annotation.PostMapping; // Importar PostMapping
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.controle_de_locadora_vhs.entities.TapeStatus; // Importar TapeStatus
import br.edu.ifpr.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.controle_de_locadora_vhs.services.CategoryService; // Importar CategoryService
import br.edu.ifpr.controle_de_locadora_vhs.services.VHSService;

@Controller
@RequestMapping("/vhs")
public class VHSController {
    @Autowired
    VHSService vhsService;

    @Autowired
    CategoryService categoryService; // Injetar CategoryService

    // Renomeado de fndAll para findAll
    @GetMapping
    public String findAll(Model model) {
        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);
        return "vhs/list"; // Mapeia para src/main/resources/templates/vhs/list.html
    }

    @GetMapping("/new")
    public String newVHSForm(Model model) {
        model.addAttribute("vhs", new VHS());
        model.addAttribute("categories", categoryService.findAll()); // Adiciona categorias para o select
        model.addAttribute("statuses", TapeStatus.values()); // Adiciona status para o select
        return "vhs/form"; // Mapeia para src/main/resources/templates/vhs/form.html
    }

    @PostMapping("/save")
    public String saveVHS(@ModelAttribute VHS vhs, RedirectAttributes redirectAttributes) {
        // Validação básica (pode ser aprimorada com @Valid e anotações na entidade)
        if (vhs.getTitle() == null || vhs.getTitle().trim().isEmpty() ||
            vhs.getDirector() == null || vhs.getDirector().trim().isEmpty() ||
            vhs.getCategory() == null || vhs.getStatus() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Todos os campos obrigatórios devem ser preenchidos.");
            return "redirect:/vhs/new"; // Ou redirecionar para o formulário de edição se for atualização
        }

        // Definir a data de cadastro se for uma nova fita
        if (vhs.getId() == null) {
            vhs.setRegistrationDate(LocalDate.now());
        } else {
            // Se for edição, mantém a data de cadastro existente
            vhsService.findById(vhs.getId()).ifPresent(existingVHS -> {
                vhs.setRegistrationDate(existingVHS.getRegistrationDate());
            });
        }

        vhsService.save(vhs);
        redirectAttributes.addFlashAttribute("successMessage", "Fita VHS salva com sucesso!");
        return "redirect:/vhs";
    }

    @GetMapping("/edit/{id}")
    public String editVHSForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return vhsService.findById(id).map(vhs -> {
            model.addAttribute("vhs", vhs);
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("statuses", TapeStatus.values());
            return "vhs/form"; // Mapeia para src/main/resources/templates/vhs/form.html
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("errorMessage", "Fita VHS não encontrada.");
            return "redirect:/vhs";
        });
    }

    @PostMapping("/delete/{id}")
    public String deleteVHS(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        vhsService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Fita VHS excluída com sucesso!");
        return "redirect:/vhs";
    }

    // Método para mudar o status (exemplo simples, pode ser mais sofisticado)
    @PostMapping("/changeStatus/{id}")
    public String changeVHSStatus(@PathVariable Long id, @RequestParam("newStatus") TapeStatus newStatus, RedirectAttributes redirectAttributes) {
        Optional<VHS> optionalVHS = vhsService.findById(id);
        if (optionalVHS.isPresent()) {
            VHS vhs = optionalVHS.get();
            vhs.setStatus(newStatus);
            vhsService.save(vhs);
            redirectAttributes.addFlashAttribute("successMessage", "Status da fita VHS atualizado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Fita VHS não encontrada.");
        }
        return "redirect:/vhs";
    }
}