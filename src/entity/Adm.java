package entity;

import java.io.*;
import java.util.Scanner;

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
    public Adm() {
        try {
            profissoesTxt = new BufferedWriter(new FileWriter(arquivo, true));


        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    public String toString() {
        return super.toString();
    }

    public void cadastrarProfissao(String services) throws IOException {
        boolean registeredService = true;
        String service = "";
        Scanner scanner = new Scanner(System.in);
        Profissao profissao = new Profissao();

        while (registeredService) {
            registeredService = false;
            System.out.println("Cadastrando profissão!");
            System.out.println("Informe o nome da profissão: ");
            service = scanner.nextLine();
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine().trim();
                if (line.equals(service)) {
                    System.out.println("Serviço já cadastrado!");
                    registeredService = true;
                }
            }
        }
        if (!registeredService) {
            profissao.setName(service);
            profissoesTxt.write(profissao.getName());
            profissoesTxt.newLine();
            profissoesTxt.close();
            System.out.println("Serviço cadastrada com sucesso!");
        }

    }

    public void mostrarProfissao() throws IOException {
        int cont = 0;
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        System.out.println("Lista de Serviços!");
        while (br.ready()) {
            String line = br.readLine().trim();
            System.out.println((cont + 1) + ") " + line);
            cont++;
        }

    }

    public void removerProfissão() throws IOException {
        boolean registeredService = true;
        String service = "";
        Scanner scanner = new Scanner(System.in);
        Profissao profissao = new Profissao();

        mostrarProfissao();
        System.out.println("Digite o número do serviço que deseja remover: (Ex: 2)");
        while (registeredService) {
            registeredService = false;
            System.out.println("Removendo profissão!");
            System.out.println("Informe o nome da profissão: ");
            service = scanner.nextLine();
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String line = br.readLine().trim();
                if (line.equals(service)) {
                    System.out.println("Serviço já cadastrado!");
                    registeredService = true;
                }
            }
        }
        if (!registeredService) {
            profissao.setName(service);
            profissoesTxt.write(profissao.getName());
            profissoesTxt.newLine();
            profissoesTxt.close();
            System.out.println("Serviço cadastrada com sucesso!");
        }

    }

    public void arquivar() throws IOException {
        profissoesTxt.close();
    }
}