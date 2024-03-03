package com.infnet.tp2;

import com.infnet.tp2.gerenciador.Pontuacao;
import com.infnet.tp2.lista.ListaTarefa;
import com.infnet.tp2.lista.Tarefa;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

import static com.infnet.tp2.utils.utils.lerInt;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static Pontuacao pontuacao = new Pontuacao();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Exibir lista de tarefas \uD83D\uDCCB");
            System.out.println("2 - Adicionar uma tarefa ➕");
            System.out.println("Sua pontuação atual é: " + pontuacao.getPontos());
            System.out.println("3 - Ver tarefa \uD83D\uDD0D");
            System.out.println("4 - Concluir tarefa ✅");
            System.out.println("5 - Sair \uD83D\uDEAA");
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida");
                lerInt(scanner);
                continue;
            }
            switch (opcao) {
                case 1:
                    List<Tarefa> tarefas = ListaTarefa.getInstance().getTarefas();
                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas.");
                    } else {
                        listarTarefas(tarefas);
                    }
                    break;
                case 2:
                    adicionarTarefa();
                    break;
                case 3:
                    tarefas = ListaTarefa.getInstance().getTarefas();
                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas.");
                    } else {
                        System.out.println("Selecione a tarefa: ");
                        listarTarefas(tarefas);
                        int indexTarefa = lerInt(scanner);
                        if (indexTarefa >= 0 && indexTarefa <= tarefas.size()) {
                            verTarefa(indexTarefa - 1);
                        } else {
                            System.out.println("Tarefa selecionada inválida.");
                        }
                    }
                    break;
                case 4:
                    tarefas = ListaTarefa.getInstance().getTarefas();
                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas.");
                    } else {
                        System.out.println("Selecione a tarefa: ");
                        listarTarefas(tarefas);
                        int indexTarefa = lerInt(scanner);
                        if (indexTarefa >= 0 && indexTarefa <= tarefas.size()) {
                            if (tarefas.get(indexTarefa - 1).isConcluida()) {
                                System.out.println("Tarefa já concluída.");
                                break;
                            }
                            tarefas.get(indexTarefa - 1).concluir();
                            System.out.println("Tarefa concluída com sucesso.");
                            pontuacao.atualizarPontuacao(true);
                        } else {
                            System.out.println("Tarefa selecionada inválida.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida, por favor, tente novamente.");
            }

        }
    }

    public static void listarTarefas(List<Tarefa> tarefas) {
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            System.out.println(
                    (i + 1) + " - " + tarefa.getTitulo() + (tarefa.isConcluida() ? " ✅" : " ⏳")
            );
        }
    }

    public static void adicionarTarefa() {
        System.out.println("Digite o título da tarefa:");
        String titulo = scanner.nextLine();
        System.out.println("Digite a descrição da tarefa:");
        String descricao = scanner.nextLine();
        Tarefa tarefa = new Tarefa(titulo, descricao);
        ListaTarefa.getInstance().adicionarTarefa(tarefa);
        System.out.println("Tarefa adicionada com sucesso.");
    }

    public static void verTarefa(int tarefaIndex) {
        List<Tarefa> tarefas = ListaTarefa.getInstance().getTarefas();
        Tarefa tarefa = tarefas.get(tarefaIndex);
        System.out.println(tarefa.toString());
    }
}
