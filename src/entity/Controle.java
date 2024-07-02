package entity;

import com.sun.tools.javac.Main;

import java.io.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import javax.swing.*;
public class Controle {
    private BufferedWriter usuariosTxt;
    private final String arquivo = "usuarios.txt";
    private boolean logado;

    private String tipoUser;

    public Controle() {
        try {
            usuariosTxt = new BufferedWriter(new FileWriter(arquivo, true));
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }

        logado = false;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    public boolean isLogado(){
        return logado;
    }

    public void cadastrar() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();
        int opc;
        String nome, email, senha;
        System.out.println("Nome: ");
        nome = scanner.nextLine();
        System.out.println("Email: ");
        email = scanner.nextLine();
        System.out.println("Senha: ");
        senha = scanner.nextLine();
        Object[] optionsCadastro = {"Cadastrar cliente", "Cadastrar profissional"};
        opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Cadastro - usuários", 0, 2, null, optionsCadastro, optionsCadastro[0]);
        System.out.println(usuario);
        switch (opc) {
            case 0:
                usuario = new Cliente(codigoAleatorio(), nome, email, senha, "Cliente");
                break;
            case 1:
                String profissao;
                System.out.println("Informe a profissão: ");
                profissao = scanner.nextLine();
                usuario = new Profissional(codigoAleatorio(), nome, email, senha, "Profissional", profissao);
                break;
            default:
                JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
        }
        usuariosTxt.write(usuario.toString());
        usuariosTxt.newLine();
    }
    public int codigoAleatorio() throws Exception {
        Set<Integer> ids = new HashSet<>();
        try {
            FileReader fr = new FileReader("usuarios.txt");
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

    public void logar(Scanner entrada) throws IOException {
        usuariosTxt.close();
        Usuario user = new Usuario();
        boolean certo = true, emailCorrect, passwordCorrect;
        while (certo) {
            emailCorrect = false;
            passwordCorrect = false;
            try {
                System.out.println("Login");
                System.out.println("Email: ");
                user.setEmail(entrada.nextLine());
                System.out.println("Senha: ");
                user.setSenha(entrada.nextLine());
                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);

                while (br.ready()) {
                    String linha = br.readLine();
                    String[] campos = linha.split(";");
                    if (campos[3].equals(user.getEmail())) {
                        emailCorrect = true;
                        if (campos[4].equals(user.getSenha())) {
                            passwordCorrect = true;
                            certo = false;
                            setTipoUser(campos[0]);
                            System.out.println("Login feito com sucesso!");
                            break;

                        }
                    }
                }
                if(!emailCorrect) {
                    throw new IllegalArgumentException("Email incorreto!");
                }
                if(!passwordCorrect){
                    throw new IllegalArgumentException("Senha incorreta!");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }
}