package entity;

import java.io.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
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

    public int codigoAleatorio() {
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

        int cod;
        Random random = new Random();
        do {
            cod = random.nextInt(100) + 1;
        } while (ids.contains(cod));

        return cod;
    }



}
