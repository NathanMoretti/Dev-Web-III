// src/main/java/br/edu/ifpr/controle_de_locadora_vhs/services/CategoryService.java
package br.edu.ifpr.controle_de_locadora_vhs.services;

import br.edu.ifpr.controle_de_locadora_vhs.entities.Category;
import br.edu.ifpr.controle_de_locadora_vhs.entities.VHS; // Import VHS
import br.edu.ifpr.controle_de_locadora_vhs.repositories.CategoryRepository;
import br.edu.ifpr.controle_de_locadora_vhs.repositories.VHSRepository; // Import VHSRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VHSRepository vhsRepository; // Autowire VHSRepository

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category categoryToDelete = categoryOptional.get();

            List<VHS> associatedVhsTapes = vhsRepository.findByCategory(categoryToDelete);

            for (VHS vhs : associatedVhsTapes) {
                vhs.setCategory(null);
                vhsRepository.save(vhs);
            }

            categoryRepository.deleteById(id);
        }
    }
}