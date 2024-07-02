package entity;

import main.Main;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Adm extends Usuario{
    private BufferedWriter profissoesTxt;
    private final String arquivo = "profissoes.txt";
    public Adm(int codigo, String nome, String email, String senha, String tipo) throws IOException {
        super(codigo, nome, email, senha, tipo);
        try {
            profissoesTxt = new BufferedWriter(new FileWriter(arquivo, true));


        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }
    public Adm() throws IOException {
        super();
        try {
            profissoesTxt = new BufferedWriter(new FileWriter(arquivo, true));
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    public String toString() {
        return super.toString();
    }


    public void cadastrarProfissao() throws Exception {
        boolean registeredService = true;

        String service = "";
        Profissao profissao = new Profissao();
        JTextField profissaoField = new JTextField();
        Object[] opcoes = {"OK", "Voltar"};
        while (true){
           int opc = JOptionPane.showOptionDialog(null, profissaoField, "Cadastro - Profissão", 0, 2, null, opcoes, opcoes[0]);
            switch (opc){
                case 0:

                    service = profissaoField.getText();
                    FileReader fr = new FileReader(arquivo);
                    BufferedReader br = new BufferedReader(fr);
                    boolean ver = true;

                    if (!service.equals("")){
                        while (br.ready()) {
                            String line = br.readLine().trim();
                            if (line.equals(service)) {
                                JOptionPane.showMessageDialog(null, "Serviço já cadastrado", "alerta", JOptionPane.ERROR_MESSAGE);
                                ver = false;
                            }else{
                                ver = true;
                            }

                        }
                        if (ver){
                            profissao.setName(service);
                            profissoesTxt.write( codigoAleatorio() + ";" + profissao.getName());
                            profissoesTxt.newLine();

                            JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!!", "alerta",JOptionPane.INFORMATION_MESSAGE );
                        }
                    }
                    break;
                    // CARLIN AJEITAR PARA QUANDO ELE SAIR JÀ VOLTAR COMO ADM LOGADO
                case 1:
                    profissoesTxt.close();
                    //System.exit(0);
                    Main.restart();
                    break;

                default:
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }


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