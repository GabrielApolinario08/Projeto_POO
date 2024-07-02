package entity;

import com.sun.tools.javac.Main;

import java.io.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import javax.swing.*;
public class Controle {
    private BufferedWriter usuarioTxt;
    private final String arquivo = "usuarios.txt";
    private boolean logado;

    private String tipoUser;

    public Controle() {
        try {
            usuarioTxt = new BufferedWriter(new FileWriter(arquivo, true));
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
        usuarioTxt.write(usuario.toString());
        usuarioTxt.newLine();
    }
    public void cadastrarCliente(Scanner entrada) throws IOException {
        int cod;
        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JTextField senha = new JTextField();
        try {
            Object[] message = {
                    "Nome:", nome,
                    "Email:", email,
                    "Senha:", senha
            };

            while (true) {
                int opt = JOptionPane.showConfirmDialog(null, message, "Informe seus dados", JOptionPane.OK_CANCEL_OPTION);
                if (opt != JOptionPane.OK_OPTION) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                // EMAILL APOLINARIOO O O O O ARRUMA
                boolean verif = false;
                for (int i = 0; i < email.getText().length(); i++) {
                    if (email.getText().charAt(i) == '@') {
                        verif = true;
                        break;
                    }
                }
                if (nome.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Nome precisa ter no mínimo 2 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (!verif) {
                    JOptionPane.showMessageDialog(null, "Email incorreto.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (senha.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Senha precisa ter no mínimo 8 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    break;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showInternalMessageDialog(null,"Fim do programa!", null, JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        try {
            cod = codigoAleatorio();
            Cliente cliente = new Cliente(cod, nome.getText(), email.getText(), senha.getText(), "Cliente");
            usuarioTxt.write(cliente.toString());
            usuarioTxt.newLine();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);

        }catch(Exception e) {
            System.err.println(e.getMessage());

        }

    }
    public void cadastrarProfissional(Scanner entrada) throws IOException {
        int cod;
        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JTextField senha = new JTextField();
        JTextField profissao = new JTextField();
        try {
            Object[] message = {
                    "Nome:", nome,
                    "Email:", email,
                    "Senha:", senha,
                    "Profissão:", profissao
            };

            while (true) {
                int opt = JOptionPane.showConfirmDialog(null, message, "Informe seus dados", JOptionPane.OK_CANCEL_OPTION);
                if (opt != JOptionPane.OK_OPTION) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                // EMAILL APOLINARIOO O O O O ARRUMA
                boolean verif = false;
                for (int i = 0; i < email.getText().length(); i++) {
                    if (email.getText().charAt(i) == '@') {
                        verif = true;
                        break;
                    }
                }
                if (nome.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Nome precisa ter no mínimo 2 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (!verif) {
                    JOptionPane.showMessageDialog(null, "Email incorreto.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (senha.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Senha precisa ter no mínimo 8 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (profissao.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Profissão precisa ter no mínimo 2 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    break;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showInternalMessageDialog(null,"Fim do programa!", null, JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        try {
            cod = codigoAleatorio();
            Profissional profissional = new Profissional(cod, nome.getText(), email.getText(), senha.getText(), "Profissional", profissao.getText());
            usuarioTxt.write(profissional.toString());
            usuarioTxt.newLine();
            JOptionPane.showMessageDialog(null, "Profissional cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
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
        usuarioTxt.close();
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