package com.infnet.tp2.gerenciador;

import java.time.LocalDate;

public class Pontuacao {
    private int pontos;
    private LocalDate ultimoDia;
    private int tarefasConcluidasNoDia;

    public Pontuacao() {
        this.pontos = 0;
        this.ultimoDia = LocalDate.now();
        this.tarefasConcluidasNoDia = 0;
    }

    public int getPontos() {
        return pontos;
    }

    public LocalDate getUltimoDia() {
        return ultimoDia;
    }

    public void setUltimoDia(LocalDate ultimoDia) {
        this.ultimoDia = ultimoDia;
        this.tarefasConcluidasNoDia = 0;
    }

    public void atualizarPontuacao(boolean tarefaConcluida) {
        LocalDate hoje = LocalDate.now();
        if (hoje.equals(ultimoDia)) {
            if (tarefaConcluida) {
                tarefasConcluidasNoDia++;
                pontos++;
                if (tarefasConcluidasNoDia == 3) {
                    pontos++;
                } else if (tarefasConcluidasNoDia == 5) {
                    pontos += 2;
                }
            }
        } else {
            if (tarefasConcluidasNoDia == 0 && !tarefaConcluida) {
                pontos--;
            }
        }
        ultimoDia = hoje;
    }

}
