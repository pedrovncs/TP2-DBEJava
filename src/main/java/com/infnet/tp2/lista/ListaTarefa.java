package com.infnet.tp2.lista;

import java.util.ArrayList;
import java.util.List;

public class ListaTarefa {
    private static ListaTarefa instancia = null;
    private List<Tarefa> tarefas;

    private ListaTarefa() {
        tarefas = new ArrayList<>();
    }

    public static ListaTarefa getInstance() {
        if (instancia == null) {
            instancia = new ListaTarefa();
        }
        return instancia;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

public void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }

}
