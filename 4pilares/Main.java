import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("Projeto: 4 pilares de OOP");
        System.out.println("- Encapsulamento: atributos privados em Bicho");
        System.out.println("- Heranca: Cachorro e Gato herdam de Bicho");
        System.out.println("- Abstracao: metodos abstratos em Bicho");
        System.out.println("- Polimorfismo: mesmo metodo, comportamentos diferentes\n");

        System.out.println("OBJETIVO: fazer o bicho chegar a idade " + Bicho.IDADE_OBJETIVO + ".");
        System.out.println("Voce perde se a fome ou o cansaco chegarem a " + Bicho.LIMITE
                + ", se a felicidade chegar a 0,");
        System.out.println("ou se a vontade de ir ao banheiro ou a sujeira chegarem a " + Bicho.LIMITE + ".\n");

        System.out.println("Digite o nome do cachorro:");
        String nomeCachorro = entrada.nextLine();

        System.out.println("Digite o nome do gato:");
        String nomeGato = entrada.nextLine();

        Bicho[] bichos = {
                new Cachorro(nomeCachorro),
                new Gato(nomeGato)
        };

        while (true) {
            Bicho bichoAtual = escolherBicho(entrada, bichos);
            if (bichoAtual == null) {
                System.out.println("Escolha invalida de bicho. Tente novamente.\n");
                continue;
            }

            System.out.println("\nEscolha uma ação:");
            System.out.println("1. Alimentar " + bichoAtual.getNome());
            System.out.println("2. Brincar com " + bichoAtual.getNome());
            System.out.println("3. Verificar status");
            System.out.println("4. Usar habilidade especial");
            System.out.println("5. Descansar");
            System.out.println("6. Levar ao banheiro");
            System.out.println("7. Dar banho");
            System.out.println("8. Demonstrar polimorfismo");
            System.out.println("9. Sair");

            if (!entrada.hasNextInt()) {
                entrada.nextLine();
                System.out.println("Digite um numero valido.\n");
                continue;
            }
            int escolha = entrada.nextInt();
            entrada.nextLine();

            // So passa o tempo quando uma ação realmente acontece
            boolean tempoPassa = true;

            switch (escolha) {
                case 1:
                    bichoAtual.alimentar();
                    break;
                case 2:
                    bichoAtual.brincar();
                    break;
                case 3:
                    bichoAtual.verificarStatus();
                    tempoPassa = false;
                    break;
                case 4:
                    bichoAtual.habilidadeEspecial();
                    break;
                case 5: {
                    System.out.println("Por quantas horas " + bichoAtual.getNome()
                            + " vai descansar? (1 a " + Bicho.HORAS_PARA_DESCANSO_TOTAL
                            + ", com " + Bicho.HORAS_PARA_DESCANSO_TOTAL + " fica totalmente descansado)");
                    if (!entrada.hasNextInt()) {
                        entrada.nextLine();
                        System.out.println("Digite um numero valido.");
                        tempoPassa = false;
                        break;
                    }
                    int horas = entrada.nextInt();
                    entrada.nextLine();
                    try {
                        bichoAtual.descansar(horas);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        tempoPassa = false;
                    }
                    break;
                }
                case 6:
                    bichoAtual.usarBanheiro();
                    break;
                case 7:
                    bichoAtual.darBanho();
                    break;
                case 8:
                    demonstrarPolimorfismo(bichos);
                    break;
                case 9:
                    System.out.println("Encerrando o projeto didatico. Ate mais!");
                    entrada.close();
                    return;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    tempoPassa = false;
            }

            if (!tempoPassa) {
                continue;
            }

            bichoAtual.passarTempo();

            if (verificarFimDeJogo(bichos, bichoAtual)) {
                entrada.close();
                return;
            }
        }
    }

    // Retorna true quando o jogo terminou
    private static boolean verificarFimDeJogo(Bicho[] bichos, Bicho bichoAtual) {
        for (Bicho bicho : bichos) {
            if (bicho.perdeu()) {
                System.out.println("\nFIM DE JOGO! " + bicho.motivoDaDerrota());
                bicho.verificarStatus();
                return true;
            }
        }

        if (bichoAtual.venceu()) {
            System.out.println("\nPARABENS! " + bichoAtual.getNome() + " chegou a idade "
                    + Bicho.IDADE_OBJETIVO + " e voce venceu o jogo!");
            bichoAtual.verificarStatus();
            return true;
        }
        return false;
    }

    private static Bicho escolherBicho(Scanner entrada, Bicho[] bichos) {
        System.out.println("\nEscolha o bicho:");
        for (int i = 0; i < bichos.length; i++) {
            System.out.println((i + 1) + ". " + bichos[i].getNome() + " (" + bichos[i].getClass().getSimpleName() + ")");
        }

        if (!entrada.hasNextInt()) {
            entrada.nextLine();
            return null;
        }

        int indice = entrada.nextInt();
        entrada.nextLine();

        if (indice < 1 || indice > bichos.length) {
            return null;
        }
        return bichos[indice - 1];
    }

    private static void demonstrarPolimorfismo(Bicho[] bichos) {
        System.out.println("\nDemonstracao de polimorfismo:");
        for (Bicho bicho : bichos) {
            bicho.interagir();
            bicho.habilidadeEspecial();
        }
    }
}