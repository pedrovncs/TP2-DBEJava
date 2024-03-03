package com.infnet.tp2;

import com.infnet.tp2.gerenciador.Pontuacao;
import com.infnet.tp2.lista.ListaTarefa;
import com.infnet.tp2.lista.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private ListaTarefa listaTarefa;
    private Pontuacao pontuacao;

    @BeforeEach
    void setUp() {
        listaTarefa = ListaTarefa.getInstance();
        listaTarefa.getTarefas().clear();
        pontuacao = new Pontuacao();
    }

    @Test
    @DisplayName("Teste adicionar tarefa")
    void testAdicionarTarefa() {
        Tarefa tarefa = new Tarefa("Título da tarefa", "Descrição da tarefa");
        listaTarefa.adicionarTarefa(tarefa);
        List<Tarefa> tarefas = listaTarefa.getTarefas();
        assertEquals(1, tarefas.size());
        assertTrue(tarefas.contains(tarefa));
    }

    @Test
    @DisplayName("Teste remover tarefa")
    void testRemoverTarefa() {
        Tarefa tarefa = new Tarefa("Título da tarefa", "Descrição da tarefa");
        listaTarefa.adicionarTarefa(tarefa);
        listaTarefa.removerTarefa(tarefa);
        List<Tarefa> tarefas = listaTarefa.getTarefas();
        assertEquals(0, tarefas.size());
        assertFalse(tarefas.contains(tarefa));
    }

    @Test
    @DisplayName("Teste listar tarefas")
    void testListarTarefas() {
        Tarefa tarefa1 = new Tarefa("Tarefa 1", "Descrição da tarefa 1");
        Tarefa tarefa2 = new Tarefa("Tarefa 2", "Descrição da tarefa 2");
        listaTarefa.adicionarTarefa(tarefa1);
        listaTarefa.adicionarTarefa(tarefa2);
        List<Tarefa> tarefas = listaTarefa.getTarefas();
        assertEquals(2, tarefas.size());
        assertTrue(tarefas.contains(tarefa1));
        assertTrue(tarefas.contains(tarefa2));
    }

    @Test
    @DisplayName("Teste obter tarefa específica")
    void testObterTarefaEspecifica() {
        Tarefa tarefa1 = new Tarefa("Tarefa 1", "Descrição da tarefa 1");
        listaTarefa.adicionarTarefa(tarefa1);
        Tarefa tarefaObtida = listaTarefa.getTarefas().get(0);
        assertEquals(tarefa1, tarefaObtida);
    }

    @Test
    @DisplayName("Teste concluir tarefa")
    void testConcluirTarefa() {
        Tarefa tarefa = new Tarefa("Título da tarefa", "Descrição da tarefa");
        assertFalse(tarefa.isConcluida());
        tarefa.concluir();
        assertTrue(tarefa.isConcluida());
        assertEquals(LocalDate.now(), tarefa.getDataConclusao());
    }

    @Test
    @DisplayName("Teste atualizar pontuação")
    void testAtualizarPontuacao() {
        pontuacao.atualizarPontuacao(true);
        assertEquals(1, pontuacao.getPontos());

        // Simula o avanço para o próximo dia
        pontuacao.atualizarPontuacao(true);
        assertEquals(2, pontuacao.getPontos());
    }

    @Test
    @DisplayName("Teste pontuação por concluir uma tarefa")
    void testPontuacaoPorConcluirUmaTarefa() {
        pontuacao.atualizarPontuacao(true);
        assertEquals(1, pontuacao.getPontos());
    }

    @Test
    @DisplayName("Teste pontuação por concluir três tarefas")
    void testPontuacaoPorConcluirTresTarefas() {
        for (int i = 0; i < 3; i++ ){
            pontuacao.atualizarPontuacao(true);
        }
        assertEquals(4, pontuacao.getPontos()); //3 pontos das tarefas + 1 ponto por concluir 3 tarefas
    }

    @Test
    @DisplayName("Teste pontuação por concluir cinco tarefas")
    void testPontuacaoPorConcluirCincoTarefas() {
        for (int i = 0; i < 5; i++) {
            pontuacao.atualizarPontuacao(true);
        }
        assertEquals(8, pontuacao.getPontos()); //4 pontos pelas 3 primeiroas + 2 pelas 5 tarefas +2 por concluir 5 no mesmo dia
    }

    @Test
    @DisplayName("Teste pontuação por não concluir ao menos uma tarefa")
    void testPontuacaoPorNaoConcluirTarefa() {
        pontuacao.atualizarPontuacao(true);
        LocalDate outroDia = LocalDate.now().plusDays(1);
        pontuacao.setUltimoDia(outroDia);
        pontuacao.atualizarPontuacao(false);
        assertEquals(0, pontuacao.getPontos());
    }
}
