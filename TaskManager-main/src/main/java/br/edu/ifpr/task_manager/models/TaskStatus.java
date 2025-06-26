package br.edu.ifpr.task_manager.models;

public enum TaskStatus {
    EM_ANDAMENTO("Em Andamento"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado");

    private String descricao;

    TaskStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}