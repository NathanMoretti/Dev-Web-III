package br.edu.ifpr.task_manager.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Task {

    private Long id;
    private String titulo;
    private String descricao;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate data;

    private TaskStatus taskStatus;
    
    public Task(Long id, String titulo, String descricao, LocalDate data, TaskStatus taskStatus) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.taskStatus = taskStatus;
    }

    public Task() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }


    
}
