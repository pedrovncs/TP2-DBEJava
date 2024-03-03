package com.infnet.tp2.lista;

import java.time.LocalDate;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private boolean concluida;

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
        this.concluida = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public boolean concluir() {
        if (!this.concluida) {
            this.concluida = true;
            this.dataConclusao = LocalDate.now();
            return true;
        }
        return false;
    }

    public String toString() {
        String taskInfo =
                "\n" +
                "Título: " + this.titulo +
                        "\n" + "Descrição: " + this.descricao +
                        "\n" + "Data de criação: " + this.dataCriacao +
                        "\n" + "Status: " + (this.concluida ? "Concluída" : "Pendente");
        if (this.concluida) {
            taskInfo += "\n" + "Data de conclusão: " + this.dataConclusao;
        }
        return taskInfo;
    }

}
