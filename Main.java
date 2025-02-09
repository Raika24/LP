import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        BlocoDeNotas bloco = new BlocoDeNotas();

        // Tratamento para editar uma anotação inexistente
        try {
            bloco.editarAnotacao(0, "Nova anotação editada");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Tentativa de abrir um arquivo que não existe
        try {
            BufferedReader reader = new BufferedReader(new FileReader("arquivo_inexistente.txt"));
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro: O arquivo não foi encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo.");
        }

        // Tentativa de escrever em um arquivo sem permissão
        try {
            Files.writeString(Paths.get("/root/arquivo_protegido.txt"), "Texto qualquer");
        } catch (AccessDeniedException e) {
            System.out.println("Erro: Permissão negada para escrever no arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo.");
        }

        // Tentativa de conexão com banco de dados inexistente
        try {
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3307/banco_inexistente", "usuario", "senha");
        } catch (SQLException e) {
            System.out.println("Erro: Não foi possível conectar ao banco de dados.");
        }

        // Tentativa de manipular um objeto nulo
        try {
            String texto = null;
            System.out.println(texto.length());
        } catch (NullPointerException e) {
            System.out.println("Erro: Tentativa de acessar um objeto nulo.");
        }


        // Tentativa de utilizar um método inexistente
        try {
            // System.out.println(Main.metodoInexistente()); // Descomente para erro real
            throw new NoSuchMethodException("Método não encontrado.");
        } catch (NoSuchMethodException e) {
            System.out.println("Erro: Tentativa de utilizar um método inexistente.");
        }
    }
}
