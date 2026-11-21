class Gato extends Bicho {
    //Herda o construtor da classe Bicho para inicializar o nome do gato

    public Gato(String nome) {
        super(nome);
    }

    @Override
    public String emitirSom() {
        return "Miau!";
    }

    @Override
    public void habilidadeEspecial() {
        alterarFelicidade(12);
        alterarFome(2);
        alterarCansaco(8);
        System.out.println(getNome() + " subiu no arranhador com elegancia.");
    }

    public void arranhar() {
        alterarFelicidade(8);
        alterarFome(3);
        alterarCansaco(4);
        System.out.println(getNome() + " arranhou o sofá e se sentiu melhor.");
    }
}