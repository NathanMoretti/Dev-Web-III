package br.edu.ifpr.controle_de_locadora_vhs.controllers;

import br.edu.ifpr.controle_de_locadora_vhs.entities.Category;
import br.edu.ifpr.controle_de_locadora_vhs.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "O nome da categoria não pode ser vazio.");
            if (category.getId() != null) {
                return "redirect:/categories/edit/" + category.getId();
            }
            return "redirect:/categories/new";
        }
        Optional<Category> existingCategory = categoryService.findAll().stream()
            .filter(c -> c.getName().equalsIgnoreCase(category.getName().trim()) && (category.getId() == null || !c.getId().equals(category.getId())))
            .findFirst();

        if (existingCategory.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Já existe uma categoria com este nome.");
            if (category.getId() != null) {
                return "redirect:/categories/edit/" + category.getId();
            }
            return "redirect:/categories/new";
        }
        
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "Categoria salva com sucesso!");
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return categoryService.findById(id).map(category -> {
            model.addAttribute("category", category);
            return "category/form";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("errorMessage", "Categoria não encontrada.");
            return "redirect:/categories";
        });
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            // MUDANÇA AQUI na mensagem de sucesso
            redirectAttributes.addFlashAttribute("successMessage", "Categoria excluída com sucesso! Fitas VHS associadas foram atualizadas para 'sem categoria'.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir a categoria: " + e.getMessage());
        }
        return "redirect:/categories";
    }
}