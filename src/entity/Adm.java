package entity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Adm extends Usuario{
    private BufferedWriter profissoesTxt;
    private final String arquivo = "profissoes.txt";
    public Adm(int codigo, String nome, String email, String senha, String tipo) {
        super(codigo, nome, email, senha, tipo);
        try {
            profissoesTxt = new BufferedWriter(new FileWriter(arquivo, true));


        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    public String toString() {
        return super.toString();
    }

    public void cadastrarProfissao(String servico) throws IOException {
        while (true) {

                if (servico.length() < 2) {
                    System.out.println("Profissão deve possuir no mínimo 2 caracteres");
                } else {
                    profissoesTxt.write(servico);
                    profissoesTxt.newLine();
                    break;
                }

        }
    }

    public void arquivar() throws IOException {
        profissoesTxt.close();
    }
}
