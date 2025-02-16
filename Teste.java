import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

class AnotacaoTest {
    private Anotacao anotacao;

    @BeforeEach
    void setUp() {
        anotacao = new Anotacao("Texto inicial");
    }

    @Test
    void testCriacaoAnotacao() {
        assertNotNull(anotacao.getId());
        assertEquals("Texto inicial", anotacao.getTexto());
        assertEquals(LocalDate.now(), anotacao.getDataCriacao());
        assertTrue(anotacao.isAtiva());
    }

    @Test
    void testEditarTexto() {
        anotacao.setTexto("Novo texto");
        assertEquals("Novo texto", anotacao.getTexto());
    }

    @Test
    void testDesativarAnotacao() {
        anotacao.desativar();
        assertFalse(anotacao.isAtiva());
    }
}

class BlocoDeNotasTest {
    private BlocoDeNotas blocoDeNotas;

    @BeforeEach
    void setUp() {
        blocoDeNotas = new BlocoDeNotas();
    }

    @Test
    void testAdicionarAnotacao() {
        blocoDeNotas.adicionarAnotacao("Nova anotação");
        assertEquals(1, blocoDeNotas.listarAnotacoes().size());
    }

    @Test
    void testEditarAnotacaoValida() {
        blocoDeNotas.adicionarAnotacao("Anotação original");
        blocoDeNotas.editarAnotacao(0, "Anotação editada");
        assertEquals("Anotação editada", blocoDeNotas.listarAnotacoes().get(0).getTexto());
    }

    @Test
    void testEditarAnotacaoInvalida() {
        blocoDeNotas.adicionarAnotacao("Anotação original");
        blocoDeNotas.removerAnotacao(0);
        blocoDeNotas.editarAnotacao(0, "Texto inválido");

        // Pegamos a anotação desativada e verificamos que o texto não foi alterado
        List<Anotacao> anotacoes = blocoDeNotas.listarAnotacoes();
        assertTrue(anotacoes.isEmpty()); // A anotação removida não deve estar na lista ativa
    }

    @Test
    void testRemoverAnotacao() {
        blocoDeNotas.adicionarAnotacao("Anotação a remover");
        blocoDeNotas.removerAnotacao(0);
        assertTrue(blocoDeNotas.listarAnotacoes().isEmpty());
    }

    @Test
    void testBuscarAnotacoes() {
        blocoDeNotas.adicionarAnotacao("Importante: Comprar pão");
        blocoDeNotas.adicionarAnotacao("Trabalho da faculdade");
        List<Anotacao> resultado = blocoDeNotas.buscarAnotacoes("pão");
        assertEquals(1, resultado.size());
        assertEquals("Importante: Comprar pão", resultado.get(0).getTexto());
    }
}