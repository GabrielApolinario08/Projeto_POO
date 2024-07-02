package entity;

import java.io.*;
import java.util.*;

public class Adm extends Usuario{
<<<<<<< HEAD
    private BufferedWriter profissoesTxt;
    private final String arquivo = "profissoes.txt";
=======
>>>>>>> ee53bd30f6d40f4a6a1791cb197873e51b7c2c94
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


    public void cadastrarProfissao(String service) throws Exception {
        boolean registeredService = true;

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
            profissoesTxt.write(codigoAleatorio() + ";" + profissao.getName());
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
            String[] campos;
            campos = line.split(";");
            System.out.println("Código: " + campos[0] + " Profissão: " + campos[1]);
            cont++;
        }

    }

    public void removerProfissao() throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String[]> allLines = new ArrayList<>();
        String[] lineRemove = new String[2];
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        while (br.ready()) {
            String line = br.readLine();
            String[] campos = line.split(";");
            allLines.add(campos);
        }


        mostrarProfissao();
        System.out.println("Digite o código da profissão que quer remover: ");
        String cod = scanner.nextLine();
        for (String[] campos:allLines) {
            if (campos[0].equals(cod)) {
                lineRemove = campos;
            }
        }
        allLines.remove(lineRemove);
        profissoesTxt = new BufferedWriter(new FileWriter(arquivo));
        for (String[] campos:allLines) {
            profissoesTxt.write(campos[0] + ";" + campos[1]);
            profissoesTxt.newLine();
        }
        System.out.println("Serviço removido com sucesso!");
        profissoesTxt.close();
    }

    public int codigoAleatorio() throws Exception {
        Set<Integer> ids = new HashSet<>();
        try {
            FileReader fr = new FileReader("profissoes.txt");
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                String linha = br.readLine();
                int p1 = linha.indexOf(';');
                int p2 = linha.indexOf(';', p1 + 1);
                if (p1 != -1 && p2 != -1) {
                    int id = Integer.parseInt(linha.substring(p1 + 1, p2));
                    ids.add(id);
                }
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ids.size() == 1000) {
            throw new Exception("Não há IDs disponíveis.");
        }

        int cod;
        Random random = new Random();
        do {
            cod = random.nextInt(1000) + 1;
        } while (ids.contains(cod));

        return cod;
    }

    public void arquivar() throws IOException {
        profissoesTxt.close();
    }
}