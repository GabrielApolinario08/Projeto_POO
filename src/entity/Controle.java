package entity;

import java.io.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
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
        int cod;
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
        try {
            cod = codigoAleatorio();
            Cliente cliente = new Cliente(cod, nome, email, senha, "Cliente");
            usuarioTxt.write(cliente.toString());
            usuarioTxt.newLine();
            System.out.println("Cliente cadastrado com sucesso!");

        }catch(Exception e) {
            System.err.println(e.getMessage());

        }

    }
    public void cadastrarProfissional(Scanner entrada) throws IOException {
        int cod;
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
        try {
            cod = codigoAleatorio();
            Profissional profissional = new Profissional(cod, nome, email, senha, "Profissional", profissao);
            usuarioTxt.write(profissional.toString());
            usuarioTxt.newLine();
            System.out.println("Profissional cadastrado com sucesso!");
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
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
        boolean qlqr = true;
        usuarioTxt.close ();
        Usuario user = new Usuario();


        while (qlqr) {
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
                            qlqr = false;
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
