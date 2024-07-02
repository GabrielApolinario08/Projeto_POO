package entity;

import java.io.*;
import java.util.*;

public class ControleServicos {
    private BufferedWriter profissoesTxt;
    private final String arquivo = "profissoes.txt";

    public ControleServicos() {
        try {
            profissoesTxt = new BufferedWriter(new FileWriter(arquivo, true));


        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    public boolean cadastrarProfissaoAdm(Profissao profissao) throws Exception {
        boolean registeredService = true;
        profissao.setCodigo(codigoAleatorio());
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        while (br.ready()) {
            String linhaToda = br.readLine().trim();
            String[] nome = linhaToda.split(";");
            if (nome[1].equals(profissao.getName())) {
                return false;
            }
        }
        profissoesTxt.write(profissao.getCodigo() + ";" + profissao.getName());
        profissoesTxt.newLine();
        profissoesTxt.close();
        return true;

    }

    public String[] mostrarProfissao() throws IOException {
        List<String> allServices = new ArrayList<>();
        int cont = 0;
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        System.out.println("Lista de Serviços!");
        while (br.ready()) {
            String line = br.readLine();
            String[] campos = line.split(";");
            allServices.add(campos[0] + ") " + campos[1]);
            cont++;
        }
        return allServices.toArray(new String[0]);
    }

    public void deletServicoControle(String itemDeletado) throws IOException {
        String cod = "", lineExcluida = "";
        for (int i = 0 ; i < itemDeletado.length() ; i++) {
            if (itemDeletado.charAt(i) != ')') {
                cod += itemDeletado.charAt(i);
            } else {
                break;
            }
        }
        ArrayList<String> allLines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        while (br.ready()) {
            String line = br.readLine();
            String[] campos = line.split(";");
            allLines.add(line);
            if (campos[0].equals(cod)) {
                lineExcluida = line;
            }
        }

        allLines.remove(lineExcluida);
        profissoesTxt = new BufferedWriter(new FileWriter(arquivo));
        for (String line : allLines) {
            profissoesTxt.write(line);
            profissoesTxt.newLine();
        }
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

    public boolean codigoExiste(String codigo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));

        while (br.ready()) {
            String linha = br.readLine().trim();
            String[] campos = linha.split(";");
            if (campos[0].equals(codigo)) {
                return true;
            }
        }
        return false;
    }
    public void arquivar() throws IOException {
        profissoesTxt.close();
    }
}