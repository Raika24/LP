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
