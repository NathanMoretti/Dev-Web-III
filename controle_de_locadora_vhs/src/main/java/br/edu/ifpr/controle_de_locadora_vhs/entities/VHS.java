// src/main/java/br/edu/ifpr/controle_de_locadora_vhs/entities/VHS.java
package br.edu.ifpr.controle_de_locadora_vhs.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class VHS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String imageUrl; // Caminho ou URL da imagem (opcional)
    private String director; // <-- ADICIONE ESTA LINHA
    private LocalDate registrationDate;

    @ManyToOne(optional = true) // Make the relationship optional
    @JoinColumn(name = "category_id", nullable = true) // Make the foreign key column nullable
    private Category category;

    @Enumerated(EnumType.STRING)
    private TapeStatus status;
}