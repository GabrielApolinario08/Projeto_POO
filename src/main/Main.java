package main;

import entity.Adm;
import entity.Cliente;
import entity.Controle;
import entity.Usuario;

import javax.swing.*;
import java.io.*;
public class Main {
    static String tipoUser;
    static Usuario user;

    static {
        try {
            user = new Usuario();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Controle controle = new Controle();
    public static void main(String[] args) throws Exception {
        BufferedWriter usuariosTxt;
        int opc;

        boolean continueOuterLoop = true;
        do {
            System.out.println(controle.isLogado());
            if (!controle.isLogado()){
                try {
                    Object[] optionsMenu = {"Entrar", "Cadastrar", "Sair"};
                    opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Menu", 0, 3, null, optionsMenu, optionsMenu[0]);

                    switch (opc) {
                        case 0:
                            controle.logar();
                            controle.setLogado(true);
                            tipoUser = controle.getTipoUser();
                            break;
                        case 1:
                            try {
                                cadastrar();
                            } catch (NumberFormatException e) {
                                JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        default:
                            JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } //para quem já tá logado (ADM)
            else {
                // USER = ADM
                if (controle.getTipoUser().equals("ADM")) {
                    Adm adm = new Adm();

                    Object[] optionsCadastroProfissao = {"Cadastrar profissão", "Cadastrar ADM", "Sair"};
                    while (true) {
                        opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Menu - ADM", 0, 3, null, optionsCadastroProfissao, optionsCadastroProfissao[0]);
                        switch (opc) {
                            case 0:
                                adm.cadastrarProfissao();
                                break;
                            case 1:
                                controle.cadastrarAdm();
                                break;
                            case 2:
                                adm.arquivar();
                                JOptionPane.showMessageDialog(null, "Deslogando!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                                controle.setLogado(false);
                                continueOuterLoop = true;
                                break;

                            default:
                                JOptionPane.showInternalMessageDialog(null,"Operação cancelada, fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                        }
                        if (continueOuterLoop) {
                            break;  // Sai do loop interno
                        }
                    }
                // USER = PROFISSIONAL
                } else if(controle.getTipoUser().equals("Profissional")) {
                    System.out.println("PROFISSAAAAAAAAAAAAA");
                    break;
                // USER = CLIENTE
                } else {
                    System.out.println("CLIENTEEEEEEEEEEEEE");
                    break;
                }
            }
        } while (true);
    }

    public static void restart() throws Exception {
        main(null);
    }

    //CARLIN METODO INUTILL (VOLTAR JÀ COM O SETLOGADO TRUE PARA ELE JÀ VOLTAR COMO LOGADO)
    public static void restartLogado() throws Exception {

        Controle controle = new Controle();
        controle.setLogado(true);
        controle.setTipoUser(tipoUser);
        main(null);
    }

    public static void cadastrar() throws Exception {
        Object[] optionsMenu = {"Cliente", "Profissional", "Voltar"};
        int opc = JOptionPane.showOptionDialog(null,"Cadastrar como:", "Menu - cadastrar", 0, 3, null, optionsMenu, optionsMenu[0]);
        Object[] optionsCad;
        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JTextField senha = new JTextField();

        switch (opc) {
            case -1:
                JOptionPane.showMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            case 0:
                optionsCad = new Object[]{
                        "Nome:", nome,
                        "Email:", email,
                        "Senha:", senha
                };

                while (true) {
                    try {
                        JOptionPane.showConfirmDialog(null, optionsCad, "Cadastrar cliente", JOptionPane.OK_CANCEL_OPTION);
                        user = new Cliente(controle.codigoAleatorio(), nome.getText(), email.getText(), senha.getText(), "Cliente");
                        controle.cadastrarControle(user);
                        JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
        }

    }

}