package entity;

import java.io.*;
import java.util.Scanner;

public class Controle {
    private BufferedWriter usuarioTxt;
    private final String arquivo = "usuarios.txt";

    public Controle() {
        try {
            usuarioTxt = new BufferedWriter(new FileWriter(arquivo, true));
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    public void cadastrarCliente(Scanner entrada) throws IOException {
            System.out.println("Cadastrar cliente");
            System.out.print("Nome: ");
            String nome = entrada.nextLine();
            System.out.print("Email: ");
            String email = entrada.nextLine();
            String senha;
            while (true) {
                System.out.print("Senha: ");
                senha = entrada.nextLine();
                if (senha.length() < 8) {
                    System.out.println("Senha precisa ter no mínimo 8 caracteres.");
                } else {
                    break;
                }
            }
            Cliente cliente = new Cliente(01, nome, email, senha, "Cliente");
            usuarioTxt.write(cliente.toString());
            usuarioTxt.newLine();
            System.out.println("Cliente cadastrado com sucesso!");
    }
    public void cadastrarProfissional(Scanner entrada) throws IOException {
        System.out.println("Cadastrar profissional");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("Email: ");
        String email = entrada.nextLine();
        String senha;
        while (true) {
            System.out.print("Senha: ");
            senha = entrada.nextLine();
            if (senha.length() < 8) {
                System.out.println("Senha precisa ter no mínimo 8 caracteres.");
            } else {
                break;
            }
        }
        System.out.print("Área de atuação: ");
        String profissao = entrada.nextLine();
        Profissional profissional = new Profissional(01, nome, email, senha, "Profissional", profissao);
        usuarioTxt.write(profissional.toString());
        usuarioTxt.newLine();
        System.out.println("Profissional cadastrado com sucesso!");
    }

    public void logar(Scanner entrada) throws IOException {
        usuarioTxt.close();
        Usuario user = new Usuario();

        outerLoop:
        while (true) {
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
                        if (campos[4].equals(user.getSenha())) {
                            System.out.println("login feito com sucesso!");
                            break outerLoop;
                        } else {
                            throw new IllegalArgumentException("Senha inválida!");
                        }
                    } else {
                        throw new IllegalArgumentException("Email inválido!");
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
