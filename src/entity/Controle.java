package entity;

import java.io.*;
import java.util.Scanner;

public class Controle {
    private BufferedWriter usuarioTxt;

    public Controle() {
        try {
            usuarioTxt = new BufferedWriter(new FileWriter("usuarios.txt", true));
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    public void fecha() throws IOException {
        usuarioTxt.close();
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



}
