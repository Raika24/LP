import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Classe que representa uma anotação
class Anotacao {
    private static int contador = 1;
    private int id;
    private String texto;
    private LocalDate dataCriacao;
    private boolean ativa;

    public Anotacao(String texto) {
        this.id = contador++;
        this.texto = texto;
        this.dataCriacao = LocalDate.now();
        this.ativa = true;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String novoTexto) {
        this.texto = novoTexto;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void desativar() {
        this.ativa = false;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Data: " + dataCriacao + " | " + texto + (ativa ? "" : " (Removida)");
    }
}

// Classe que representa o bloco de notas
class BlocoDeNotas {
    private List<Anotacao> anotacoes;

    public BlocoDeNotas() {
        this.anotacoes = new ArrayList<>();
    }

    public void adicionarAnotacao(String texto) {
        anotacoes.add(new Anotacao(texto));
    }

    public void editarAnotacao(int indice, String novoTexto) {
        if (indice >= 0 && indice < anotacoes.size() && anotacoes.get(indice).isAtiva()) {
            anotacoes.get(indice).setTexto(novoTexto);
        } else {
            System.out.println("Anotação não encontrada ou já removida.");
        }
    }

    public void removerAnotacao(int indice) {
        if (indice >= 0 && indice < anotacoes.size() && anotacoes.get(indice).isAtiva()) {
            anotacoes.get(indice).desativar();
        } else {
            System.out.println("Anotação não encontrada ou já removida.");
        }
    }

    public List<Anotacao> buscarAnotacoes(String textoBusca) {
        return anotacoes.stream()
                .filter(a -> a.isAtiva() && a.getTexto().toLowerCase().contains(textoBusca.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Anotacao> listarAnotacoes() {
        return anotacoes.stream()
                .filter(Anotacao::isAtiva)
                .collect(Collectors.toList());
    }
}
