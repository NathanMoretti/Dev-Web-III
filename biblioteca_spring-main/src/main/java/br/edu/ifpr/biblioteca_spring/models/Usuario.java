package br.edu.ifpr.biblioteca_spring.models;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Usuario {

    private Long id;

    @Size(min = 1, max = 25, message = "O nome deve ter entre 1 e 25 caracteres. ")
    @NotBlank(message = "O nome não pode estar vazio. ")
    @Pattern(regexp = ".*[a-zA-ZáéíóúÁÉÍÓÚãõÃÕçÇ ].*", message = "O nome deve conter letras. ")
    private String nome;

    @CPF
    private String cpf;

    private boolean bloqueado;

    private LocalDate dataDeDesbloqueio;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.bloqueado = false;
        this.dataDeDesbloqueio = null;
    }

    public boolean isBloqueado() {
        if (dataDeDesbloqueio != null && LocalDate.now().isAfter(dataDeDesbloqueio)) {
            bloqueado = false;
            dataDeDesbloqueio = null;
        }
        return bloqueado;
    }

    public void bloquearAte(LocalDate data) {
        this.bloqueado = true;
        this.dataDeDesbloqueio = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public LocalDate getDataDeDesbloqueio() {
        return dataDeDesbloqueio;
    }

    public void setDataDeDesbloqueio(LocalDate dataDeDesbloqueio) {
        this.dataDeDesbloqueio = dataDeDesbloqueio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + (bloqueado ? 1231 : 1237);
        result = prime * result + ((dataDeDesbloqueio == null) ? 0 : dataDeDesbloqueio.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (bloqueado != other.bloqueado)
            return false;
        if (dataDeDesbloqueio == null) {
            if (other.dataDeDesbloqueio != null)
                return false;
        } else if (!dataDeDesbloqueio.equals(other.dataDeDesbloqueio))
            return false;
        return true;
    }

    // Getters e Setters
    
    
}
