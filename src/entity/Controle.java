package entity;

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


    public void cadastrarCliente() throws IOException {
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
                int opt = JOptionPane.showConfirmDialog(null, message, "Cadastrar Cliente", JOptionPane.OK_CANCEL_OPTION);
                if (opt != JOptionPane.OK_OPTION) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                if (nome.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Nome precisa ter no mínimo 2 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (!email.getText().contains("@") && !email.getText().contains(".")) {
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
    public void cadastrarProfissional() throws IOException {
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
                int opt = JOptionPane.showConfirmDialog(null, message, "Cadastrar profissional", JOptionPane.OK_CANCEL_OPTION);
                if (opt != JOptionPane.OK_OPTION) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                if (nome.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "Nome precisa ter no mínimo 2 caracteres", "Erro", JOptionPane.INFORMATION_MESSAGE);
                } else if (!email.getText().contains("@") && !email.getText().contains(".")) {
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
                int opt = JOptionPane.showConfirmDialog(null, dados, "Entrar", JOptionPane.OK_CANCEL_OPTION);
                if (opt != JOptionPane.OK_OPTION) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
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