import java.util.Scanner;

class BichinhoVirtual {

    static final int LIMITE = 100;
    static final int IDADE_OBJETIVO = 50;
    static final int HORAS_PARA_DESCANSO_TOTAL = 8;

    private String nome;
    private int nivelDeFome = 50;
    private int nivelFelicidade = 50;
    private int nivelCansaco = 0;
    private int idade = 0;
    private int nivelBanheiro = 0;
    private int nivelSujeira = 0;

    // Construtor para inicializar o nome do bicho
    public BichinhoVirtual(String nome) {
        this.nome = nome;
    }

    public void alimentar() {
        nivelDeFome = limitar(nivelDeFome - 10);
        nivelBanheiro = limitar(nivelBanheiro + 15);
        System.out.println(this.nome + " foi alimentado. O nível de fome diminuiu e a vontade de ir ao banheiro aumentou.");
    }

    public void brincar() {
        nivelFelicidade = limitar(nivelFelicidade + 10);
        nivelCansaco = limitar(nivelCansaco + 15);
        nivelSujeira = limitar(nivelSujeira + 15);
        System.out.println(this.nome + " está brincando e se sentindo mais feliz, mas também mais cansado e sujo.");
    }

    // Com 8 horas de descanso o bicho fica totalmente descansado
    public void descansar(int horas) {
        if (horas < 1 || horas > HORAS_PARA_DESCANSO_TOTAL) {
            throw new IllegalArgumentException("Informe de 1 a " + HORAS_PARA_DESCANSO_TOTAL + " horas de descanso.");
        }
        nivelCansaco = limitar(nivelCansaco - horas * LIMITE / HORAS_PARA_DESCANSO_TOTAL);
        System.out.println(this.nome + " descansou por " + horas + " hora(s). O cansaço diminuiu.");
    }

    public void usarBanheiro() {
        nivelBanheiro = 0;
        System.out.println(this.nome + " foi ao banheiro e se sente aliviado.");
    }

    public void darBanho() {
        nivelSujeira = 0;
        System.out.println(this.nome + " tomou banho e está limpinho.");
    }

    public void verificarStatus() {
        System.out.println("Status atual de " + this.nome + ":");
        System.out.println("Idade: " + this.idade + " / " + IDADE_OBJETIVO);
        System.out.println("Nível de fome: " + this.nivelDeFome);
        System.out.println("Nível de felicidade: " + this.nivelFelicidade);
        System.out.println("Nível de cansaço: " + this.nivelCansaco);
        System.out.println("Vontade de ir ao banheiro: " + this.nivelBanheiro);
        System.out.println("Nível de sujeira: " + this.nivelSujeira);
    }

    // Um ciclo de tempo: fome +3, felicidade -3, cansaço +10, idade +1
    public void passarTempo() {
        nivelDeFome = limitar(nivelDeFome + 3);
        nivelFelicidade = limitar(nivelFelicidade - 3);
        nivelCansaco = limitar(nivelCansaco + 10);
        idade++;
        System.out.println(this.nome + " sentiu o tempo passar. Agora tem idade " + this.idade + ".");
    }

    public String motivoDaDerrota() {
        if (nivelDeFome >= LIMITE) {
            return nome + " morreu de fome.";
        }
        if (nivelCansaco >= LIMITE) {
            return nome + " desmaiou de cansaço.";
        }
        if (nivelFelicidade <= 0) {
            return nome + " ficou triste demais.";
        }
        if (nivelBanheiro >= LIMITE) {
            return nome + " não aguentou mais e fez a necessidade fora do lugar.";
        }
        if (nivelSujeira >= LIMITE) {
            return nome + " ficou sujo demais e adoeceu.";
        }
        return null;
    }

    public boolean venceu() {
        return idade >= IDADE_OBJETIVO;
    }

    public String getNome() {
        return nome;
    }

    private int limitar(int valor) {
        return Math.max(0, Math.min(LIMITE, valor));
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("OBJETIVO: fazer o bichinho chegar à idade " + IDADE_OBJETIVO + ".");
        System.out.println("Você perde se a fome ou o cansaço chegarem a " + LIMITE + ", se a felicidade chegar a 0,");
        System.out.println("ou se a vontade de ir ao banheiro ou a sujeira chegarem a " + LIMITE + ".\n");

        System.out.println("Digite o nome do seu bichinho:");
        String nomeBicho = entrada.nextLine();
        if (nomeBicho.trim().isEmpty()) {
            nomeBicho = "Bichinho";
        }
        BichinhoVirtual bicho = new BichinhoVirtual(nomeBicho.trim());

        while (true) {
            System.out.println("\nEscolha uma ação:");
            System.out.println("1. Alimentar " + bicho.getNome());
            System.out.println("2. Brincar com " + bicho.getNome());
            System.out.println("3. Descansar");
            System.out.println("4. Levar ao banheiro");
            System.out.println("5. Dar banho");
            System.out.println("6. Verificar status");
            System.out.println("7. Sair");

            if (!entrada.hasNextInt()) {
                entrada.nextLine();
                System.out.println("Digite um número válido.");
                continue;
            }
            int escolha = entrada.nextInt();
            entrada.nextLine();

            // O tempo só passa quando uma ação realmente acontece
            boolean tempoPassa = true;

            switch (escolha) {
                case 1:
                    bicho.alimentar();
                    break;
                case 2:
                    bicho.brincar();
                    break;
                case 3: {
                    System.out.println("Por quantas horas " + bicho.getNome() + " vai descansar? (1 a "
                            + HORAS_PARA_DESCANSO_TOTAL + ", com " + HORAS_PARA_DESCANSO_TOTAL
                            + " fica totalmente descansado)");
                    if (!entrada.hasNextInt()) {
                        entrada.nextLine();
                        System.out.println("Digite um número válido.");
                        tempoPassa = false;
                        break;
                    }
                    int horas = entrada.nextInt();
                    entrada.nextLine();
                    try {
                        bicho.descansar(horas);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        tempoPassa = false;
                    }
                    break;
                }
                case 4:
                    bicho.usarBanheiro();
                    break;
                case 5:
                    bicho.darBanho();
                    break;
                case 6:
                    bicho.verificarStatus();
                    tempoPassa = false;
                    break;
                case 7:
                    System.out.println("Encerrando o jogo. Até mais!");
                    entrada.close();
                    return;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    tempoPassa = false;
            }

            if (!tempoPassa) {
                continue;
            }

            bicho.passarTempo();

            String derrota = bicho.motivoDaDerrota();
            if (derrota != null) {
                System.out.println("\nFIM DE JOGO! " + derrota);
                bicho.verificarStatus();
                entrada.close();
                return;
            }
            if (bicho.venceu()) {
                System.out.println("\nPARABÉNS! " + bicho.getNome() + " chegou à idade " + IDADE_OBJETIVO
                        + " e você venceu o jogo!");
                bicho.verificarStatus();
                entrada.close();
                return;
            }
        }
    }
}