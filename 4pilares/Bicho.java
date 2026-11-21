abstract class Bicho {

    // Constantes do jogo
    public static final int LIMITE = 100;
    public static final int IDADE_OBJETIVO = 50;
    public static final int HORAS_PARA_DESCANSO_TOTAL = 8;

    // Encapsulamento: estado interno privado, acessado por metodos controlados.
    //Modificadores de Acesso: os atributos sao privados para proteger o estado interno do bicho, e os metodos publicos ou protegidos controlam como esse estado pode ser alterado ou acessado.
    private String nome;
    public int pontosDeVida;
    private int nivelDeFome;
    private int nivelFelicidade;
    private int nivelCansaco;
    private int idade;
    private int nivelBanheiro;
    private int nivelSujeira;

    // Método Construtor. Inicializa o bicho com um nome e niveis padrao.
    public Bicho(String nome) {
        setNome(nome);
        this.nivelDeFome = 50;
        this.nivelFelicidade = 50;
        this.nivelCansaco = 0;
        this.idade = 0;
        this.nivelBanheiro = 0;
        this.nivelSujeira = 0;
    }

    // final: impede que subclasses sobrescrevam esses metodos, garantindo que a logica de acesso e modificacao do estado interno seja consistente em todas as classes filhas.
    public final String getNome() {
        return nome;
    }

    public final int getNivelDeFome() {
        return nivelDeFome;
    }

    public final int getNivelFelicidade() {
        return nivelFelicidade;
    }

    public final int getNivelCansaco() {
        return nivelCansaco;
    }

    public final int getIdade() {
        return idade;
    }

    public final int getNivelBanheiro() {
        return nivelBanheiro;
    }

    public final int getNivelSujeira() {
        return nivelSujeira;
    }

    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do bicho nao pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public void alimentar() {
        alterarFome(-10);
        alterarBanheiro(15);
        System.out.println(nome + " foi alimentado. A fome diminuiu e a vontade de ir ao banheiro aumentou.");
    }

    public void brincar() {
        alterarFelicidade(10);
        alterarCansaco(15);
        alterarSujeira(15);
        System.out.println(nome + " brincou e ficou mais feliz, mas tambem mais cansado e sujo.");
    }

    // Desafio extra: descansa por um numero de horas. Com 8 horas fica totalmente descansado.
    public void descansar(int horas) {
        if (horas < 1 || horas > HORAS_PARA_DESCANSO_TOTAL) {
            throw new IllegalArgumentException(
                    "Informe de 1 a " + HORAS_PARA_DESCANSO_TOTAL + " horas de descanso.");
        }
        int reducao = horas * LIMITE / HORAS_PARA_DESCANSO_TOTAL;
        alterarCansaco(-reducao);
        System.out.println(nome + " descansou por " + horas + " hora(s). O cansaco diminuiu.");
    }

    public void usarBanheiro() {
        alterarBanheiro(-LIMITE);
        System.out.println(nome + " foi ao banheiro e se sente aliviado.");
    }

    public void darBanho() {
        alterarSujeira(-LIMITE);
        System.out.println(nome + " tomou banho e esta limpinho.");
    }

    public void verificarStatus() {
        System.out.println("Status de " + nome + ":");
        System.out.println("- Idade: " + idade + " / " + IDADE_OBJETIVO);
        System.out.println("- Nivel de fome: " + nivelDeFome);
        System.out.println("- Nivel de felicidade: " + nivelFelicidade);
        System.out.println("- Nivel de cansaco: " + nivelCansaco);
        System.out.println("- Vontade de ir ao banheiro: " + nivelBanheiro);
        System.out.println("- Nivel de sujeira: " + nivelSujeira);
    }

    // Um ciclo de tempo: fome +3, felicidade -3, cansaco +10, idade +1.
    public void passarTempo() {
        alterarFome(3);
        alterarFelicidade(-3);
        alterarCansaco(10);
        idade++;
        System.out.println(nome + " sentiu os efeitos do tempo passando. Agora tem idade " + idade + ".");
    }

    // Regras de derrota
    public final boolean perdeu() {
        return motivoDaDerrota() != null;
    }

    public final String motivoDaDerrota() {
        if (nivelDeFome >= LIMITE) {
            return nome + " morreu de fome.";
        }
        if (nivelCansaco >= LIMITE) {
            return nome + " desmaiou de cansaco.";
        }
        if (nivelFelicidade <= 0) {
            return nome + " ficou triste demais.";
        }
        if (nivelBanheiro >= LIMITE) {
            return nome + " nao aguentou mais e fez a necessidade fora do lugar.";
        }
        if (nivelSujeira >= LIMITE) {
            return nome + " ficou sujo demais e adoeceu.";
        }
        return null;
    }

    // Objetivo do jogo
    public final boolean venceu() {
        return idade >= IDADE_OBJETIVO;
    }

    protected final void alterarFome(int delta) {
        nivelDeFome = limitarEntre0e100(nivelDeFome + delta);
    }

    protected final void alterarFelicidade(int delta) {
        nivelFelicidade = limitarEntre0e100(nivelFelicidade + delta);
    }

    protected final void alterarCansaco(int delta) {
        nivelCansaco = limitarEntre0e100(nivelCansaco + delta);
    }

    protected final void alterarBanheiro(int delta) {
        nivelBanheiro = limitarEntre0e100(nivelBanheiro + delta);
    }

    protected final void alterarSujeira(int delta) {
        nivelSujeira = limitarEntre0e100(nivelSujeira + delta);
    }

    private int limitarEntre0e100(int valor) {
        return Math.max(0, Math.min(LIMITE, valor));
    }

    // Abstracao: cada tipo de bicho define seu som e habilidade especial.
    public abstract String emitirSom();

    public abstract void habilidadeEspecial();

    public void interagir() {
        System.out.println(nome + " diz: " + emitirSom());
    }
}