package br.edu.ifpr.controle_de_locadora_vhs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.controle_de_locadora_vhs.entities.VHS;
import br.edu.ifpr.controle_de_locadora_vhs.entities.Category;

import java.util.List;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {
    List<VHS> findByCategory(Category category);
}