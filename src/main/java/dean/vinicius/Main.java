package dean.vinicius;

import java.util.Random;
import java.util.Scanner;

/*
Escolhi java pois é a minha linguagem preferida e a que tenho mais projetos
também acho ela uma linguagem muito completa para backend, principalmente com o framework Spring
e além disso é muito usada no mercado.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random aleatorio = new Random();
        String[] jokenpo = {"Pedra", "Papel", "Tesoura"};
        String escolhaMenu, jogada, resultado;
        int vitoriasJogador = 0, vitoriasJogadorCamp = 0, vitoriasCpu = 0, vitoriasCpuCamp = 0, empates = 0, campeonatosJogador = 0, campeonatosCPU = 0;

        do {
            System.out.println("=================================");
            System.out.println("         JOGO JOKENPO");
            System.out.println("=================================");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Jogar Novo Jogo");
            System.out.println("2 - Jogar Novo Campeonato");
            System.out.println("0 - Sair");
            System.out.print("Digite sua jogada: ");

            escolhaMenu = scanner.nextLine();
            System.out.println();

            switch (escolhaMenu) {
                case "1":
                    do {
                        System.out.println("=================================");
                        System.out.println("        JOKENPO");
                        System.out.println("=================================");
                        System.out.println("Escolha uma opção:");
                        System.out.println("1 - Pedra");
                        System.out.println("2 - Papel");
                        System.out.println("3 - Tesoura");
                        System.out.println("0 - Voltar ao menu principal");
                        System.out.print("Digite sua jogada: ");

                        jogada = scanner.nextLine();
                        System.out.println();

                        if (jogada.equals("0")) {
                            System.out.println("Voltando ao menu principal...\n");
                            break;
                        }

                        int escolhaUser = Integer.parseInt(jogada) - 1;
                        int EscolhaCpu = aleatorio.nextInt(3);

                        System.out.println("Você escolheu " + jokenpo[escolhaUser]);

                        System.out.println("A IA escolheu " + jokenpo[EscolhaCpu]);


                        if (escolhaUser == EscolhaCpu) {
                            resultado = "Empate!";
                            empates++;
                        } else if ((escolhaUser == 0 && EscolhaCpu == 2) ||
                                (escolhaUser == 1 && EscolhaCpu == 0) ||
                                (escolhaUser == 2 && EscolhaCpu == 1)) {
                            resultado = "Você venceu!";
                            vitoriasJogador++;
                        } else {
                            resultado = "A IA venceu!";
                            vitoriasCpu++;
                        }

                        System.out.println(resultado);

                        System.out.println("Placar: Você " + vitoriasJogador + " - IA " + vitoriasCpu + " - Empates " + empates);
                        System.out.println();
                        Thread.sleep(5000);


                    } while (!jogada.equals("0"));
                    break;

                case "2":
                    System.out.println("=================================");
                    System.out.println("    MODO CAMPEONATO (Melhor de 5)");
                    System.out.println("=================================");
                    vitoriasJogadorCamp = 0;
                    vitoriasCpuCamp = 0;

                    while (vitoriasJogadorCamp < 3 && vitoriasCpuCamp < 3) {
                        System.out.println("Escolha uma opção:");
                        System.out.println("1 - Pedra");
                        System.out.println("2 - Papel");
                        System.out.println("3 - Tesoura");
                        System.out.print("Digite sua jogada: ");

                        jogada = scanner.nextLine();
                        System.out.println();

                        int escolhaUser = Integer.parseInt(jogada) - 1;
                        int escolhaCpu = aleatorio.nextInt(3);

                        System.out.println("Você escolheu " + jokenpo[escolhaUser]);

                        System.out.println("A IA escolheu " + jokenpo[escolhaCpu]);

                        if (escolhaUser == escolhaCpu) {
                            System.out.println("Empate!");
                            empates++;
                        } else if ((escolhaUser == 0 && escolhaCpu == 2) || (escolhaUser == 1 && escolhaCpu == 0) || (escolhaUser == 2 && escolhaCpu == 1)) {
                            System.out.println("Você venceu esta rodada!");
                            vitoriasJogadorCamp++;
                            vitoriasJogador++;
                        } else {
                            System.out.println("A IA venceu esta rodada!");
                            vitoriasCpuCamp++;
                            vitoriasCpu++;
                        }

                        System.out.println("Placar: Você " + vitoriasJogadorCamp + " - IA " + vitoriasCpuCamp);
                        Thread.sleep(5000);

                        System.out.println();
                    }

                    if (vitoriasJogadorCamp == 3) {
                        System.out.println("=================================");
                        System.out.println("  VOCÊ É O CAMPEÃO DO CAMPEONATO!");
                        System.out.println("=================================");
                        campeonatosJogador++;
                    } else {
                        System.out.println("=================================");
                        System.out.println("  A IA É A CAMPEÃ DO CAMPEONATO!");
                        System.out.println("=================================");
                        campeonatosCPU++;
                    }
                    Thread.sleep(5000);


                    break;

                case "0":
                    System.out.println("Saindo do jogo. Até a próxima!");
                    Estatisticas(vitoriasJogador, vitoriasCpu, empates, campeonatosJogador, campeonatosCPU);
                    break;

                default:
                    System.out.println("Escolha inválida! Tente novamente.\n");
                    break;
            }

        } while (!escolhaMenu.equals("0"));

        scanner.close();
    }



    public static void Estatisticas(int vitoriasUser, int vitoriasCpu, int empates, int campeonatosJogador, int campeonatosCpu) {
        int totalJogos = vitoriasUser + vitoriasCpu + empates;

        if (totalJogos == 0) {
            System.out.println("Nenhum jogo foi jogado nesta sessão.");
            return;
        }

        double porcentagemVitoriasUser = (vitoriasUser * 100.0)/totalJogos;
        double porcentagemVitoriasCpu = (vitoriasCpu * 100.0) /totalJogos;
        double porcentagemEmpates = (empates * 100.0)/ totalJogos;

        System.out.println("\n===== Estatísticas da Sessão =====");
        System.out.println("Total de jogos: " + totalJogos);
                System.out.printf("Porcentagem de vitórias do jogador: %.2f%%\n", porcentagemVitoriasUser);
        System.out.printf("Porcentagem de vitórias da IA: %.2f%%\n", porcentagemVitoriasCpu);
        System.out.printf("Porcentagem de empates: %.2f%%\n", porcentagemEmpates);
        System.out.println("Campeonatos ganhos pelo jogador: " + campeonatosJogador);
        System.out.println("Campeonatos ganhos pela IA: " + campeonatosCpu);
        System.out.println("==================================\n");
    }
}