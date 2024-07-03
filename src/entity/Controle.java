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

    public boolean isLogado() {
        return logado;
    }

    public void cadastrarControle(Usuario usuario) throws Exception {
        usuariosTxt = new BufferedWriter(new FileWriter(arquivo, true));
        usuariosTxt.write(usuario.toString());
        usuariosTxt.newLine();
        usuariosTxt.close();
    }



    public boolean emailExistente(String email) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        while (br.ready()) {
            String[] colunas = br.readLine().split(";");
            if (colunas[3].equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void cadastrarAdmControle(Adm adm) throws Exception {
        usuariosTxt = new BufferedWriter(new FileWriter(arquivo, true));
        usuariosTxt.write(adm.toString());
        usuariosTxt.newLine();
        usuariosTxt.close();
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
    public static String[] carregarProfissionais(String fileName) {
        List<String> profissionalList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linha;
            String linhaProf;
            String tipo, nome, profissao;
            int ver = 0;
            while ((linha = br.readLine()) != null) {

                String[] campos = linha.split(";");
                tipo = campos[0];
                if(tipo.equals("Profissional")){
                    linhaProf = campos[2] + "(" + campos[5] + ")";
                    profissionalList.add(linhaProf);
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profissionalList.toArray(new String[0]);
    }
    public boolean logarControle(Usuario user) throws IOException {
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        boolean emailCorrect = false, passwordCorrect = false;
        while (br.ready()) {
            String linha = br.readLine();
            String[] campos = linha.split(";");
            if (campos[3].equals(user.getEmail())) {
                emailCorrect = true;
                if (campos[4].equals(user.getSenha())) {
                    user.setTipo(campos[0]);
                    logado = true;
                    return true;
                }
            }
        }
        if (!emailCorrect) {
            throw new IllegalArgumentException("Email incorreto!");
        }
        if (!passwordCorrect) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        return false;
        /*Usuario user = new Usuario();
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
        }*/

    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

}