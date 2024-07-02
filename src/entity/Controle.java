package entity;

import main.Main;
import java.io.*;
import java.util.*;
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
        usuariosTxt.close();
    }


    private static String[] carregarProfissoesDeArquivo(String fileName) {
        List<String> profissaoList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                profissaoList.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profissaoList.toArray(new String[0]);
    }


    public void cadastrarAdm() throws IOException {
        usuariosTxt = new BufferedWriter(new FileWriter(arquivo, true));
        int cod;
        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JTextField senha = new JTextField();
        try {
            Object[] message = {
                    "Nome:", nome,
                    "Email:", email,
                    "Senha:", senha,
            };

            while (true) {
                int opt = JOptionPane.showConfirmDialog(null, message, "Cadastrar ADM", JOptionPane.OK_CANCEL_OPTION);
                if (opt != JOptionPane.OK_OPTION) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                if (nome.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Nome precisa ter no mínimo 2 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (!email.getText().contains("@") || !email.getText().contains(".")) {
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
            Adm adm = new Adm(cod, nome.getText(), email.getText(), senha.getText(), "ADM");
            usuariosTxt.write(adm.toString());
            usuariosTxt.newLine();
            JOptionPane.showMessageDialog(null, "ADM cadastrado com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            usuariosTxt.close();
        } catch(Exception e) {
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
            JOptionPane.showMessageDialog(null, "Não há IDs disponíveis.", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Não há IDs disponíveis.");
        }

        int cod;
        Random random = new Random();
        do {
            cod = random.nextInt(1000) + 1;
        } while (ids.contains(cod));

        return cod;
    }
    public void logar() throws IOException {

        Usuario user = new Usuario();
        boolean certo = true, emailCorrect, passwordCorrect;
        JTextField email = new JTextField();
        JTextField senha = new JTextField();
        while (certo) {
            emailCorrect = false;
            passwordCorrect = false;
            try {
                Object[] dados = {
                        "Email:", email,
                        "Senha:", senha
                };
                Object[] opcoes = {"OK", "Voltar", "Fechar"};

                int opt = JOptionPane.showOptionDialog(null, dados, "Entrar", 0, 3, null, opcoes, opcoes[0]);
                if (opt == 2) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else if (opt == 0) {
                    user.setEmail(email.getText());
                    user.setSenha(senha.getText());
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
                                logado= true;
                                JOptionPane.showMessageDialog(null, "Login feito com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
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
                } else{
                    Main.restart();
                }



            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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