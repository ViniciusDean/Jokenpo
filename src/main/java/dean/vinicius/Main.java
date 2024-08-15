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
        String escolhaMenu, jogada, resultado;
        int vitoriasJogador = 0, vitoriasJogadorCamp = 0, vitoriasCpu = 0,
                retorno = 0, vitoriasCpuCamp = 0, empates = 0, campeonatosJogador = 0, campeonatosCPU = 0;

        do {
            System.out.println("=================================");
            System.out.println("         JOGO JOKENPO");
            System.out.println("=================================");
            System.out.println("   Escolha uma opção:");
            System.out.println("[1] - Jogar Novo Jogo");
            System.out.println("[2] - Jogar Novo Campeonato");
            System.out.println("[0] Sair");
            System.out.print("Digite sua jogada: ");

            escolhaMenu = scanner.nextLine();
            System.out.println();

            switch (escolhaMenu) {
                case "1":
                    do {
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
                            if (jogada.isEmpty() || !jogada.matches("[0-3]") || jogada.contains(" ")
                                    || !jogada.matches("\\d")) { //aqui faço uma verificação para evitar que o programa pare de funcionar caso o input do usuario não seja o esperado
                                System.out.println("Por favor, selecione um numero entre 0 e 3, sem espaços!");
                                Thread.sleep(3000);
                            }
                        } while (jogada.isEmpty() || !jogada.matches("[0-3]") || jogada.contains(" ") || !jogada.matches("\\d")); // loop vai ficar voltando pro scanner até o input ser o que o programa espera
                        if (jogada.equals("0")) {
                            System.out.println("Voltando ao menu principal...\n");
                            break;
                        }
                        retorno = Jokenpo(jogada);
                        //aqui pegamos o retorno da função e comparamos com os ids de empate, vitoria humana ou a vitoria da IA
                        if (retorno == 001) {
                            resultado = "Empate!";
                            empates++;
                        } else if (retorno == 002) {
                            resultado = "Você venceu!";
                            vitoriasJogador++;
                        } else {
                            resultado = "A IA venceu!";
                            vitoriasCpu++;
                        }
                        System.out.println(resultado);
                        System.out.println("Placar: Você " + vitoriasJogador + " - IA " + vitoriasCpu + " - Empates " + empates);
                        Thread.sleep(5000);
                        // para que o usuario fique jogando até decidir voltar ao menu principal
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
                        retorno = Jokenpo(jogada);
                        // mesma coisa do case '1' porém aqui tem uma variavel a mais, que garante que a MD5 ocorrerá sem problemas
                        if (retorno == 001) {
                            resultado = "Empate!";
                            empates++;
                        } else if (retorno == 002) {
                            resultado = "Você venceu!";
                            vitoriasJogadorCamp++;
                            vitoriasJogador++;
                        } else {
                            resultado = "A IA venceu!";
                            vitoriasCpu++;
                            vitoriasCpuCamp++;
                        }
                        System.out.println(resultado);
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
                    System.out.println("Escolha inválida! Tente novamente.");
                    break;
            }
        // para que o usuario possa alternar entre os modos de jogo sem sair do programa
        } while (!escolhaMenu.equals("0"));

        scanner.close();
    }


    public static void Estatisticas(int vitoriasUser, int vitoriasCpu, int empates, int campeonatosJogador, int campeonatosCpu) {
        int totalJogos = vitoriasUser + vitoriasCpu + empates;

        if (totalJogos == 0) {
            System.out.println("Nenhum jogo foi jogado nesta sessão.");
            return;
        }

        double porcentagemVitoriasUser = (vitoriasUser * 100.0) / totalJogos; // aqui criamos as estatisticas que serão exibidas ao usuario no encerramento do programa
        double porcentagemVitoriasCpu = (vitoriasCpu * 100.0) / totalJogos;
        double porcentagemEmpates = (empates * 100.0) / totalJogos;

        System.out.println("\n===== Estatísticas da Sessão =====");
        System.out.println("Total de jogos: " + totalJogos);
        System.out.printf("Porcentagem de vitórias do jogador: %.2f%%\n", porcentagemVitoriasUser);
        System.out.printf("Porcentagem de vitórias da IA: %.2f%%\n", porcentagemVitoriasCpu);
        System.out.printf("Porcentagem de empates: %.2f%%\n", porcentagemEmpates);
        System.out.println("Campeonatos ganhos pelo jogador: " + campeonatosJogador);
        System.out.println("Campeonatos ganhos pela IA: " + campeonatosCpu);
        System.out.println("==================================\n");
    }

    public static int Jokenpo(String jogada) {
        Random aleatorio = new Random();
        String[] jokenpo = {"", "Pedra", "Papel", "Tesoura"};
        int escolhaUser = Integer.parseInt(jogada);
        // aqui criamos um número aleatório entre 1 e 3, que é a jogada da IA
        int escolhaCpu = aleatorio.nextInt(1, 4);
        System.out.println("Você escolheu " + jokenpo[escolhaUser]);
        // aqui comparamos as escolhas com a array para mostrar no console a jogada de cada um
        System.out.println("A IA escolheu " + jokenpo[escolhaCpu]);
        if (escolhaUser == escolhaCpu) {
            return 001;
        } else if ((escolhaUser == 1 && escolhaCpu == 3) ||
                (escolhaUser == 2 && escolhaCpu == 1) ||
                (escolhaUser == 3 && escolhaCpu == 2)) {
            return 002;
        } else {
            return 003;
        }
    }
}