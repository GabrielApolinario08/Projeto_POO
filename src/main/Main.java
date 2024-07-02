package main;

import entity.Adm;
import entity.Controle;

import javax.swing.*;
import java.io.*;
public class Main {
    static String tipoUser;
    public static void main(String[] args) throws Exception {
        Controle controle = new Controle();
        int opc;

        String nome, email, senha, tipo;
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
                                controle.cadastrar();
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

    //CARLIN METODO INUTIL (VOLTAR JÀ COM O SETLOGADO TRUE PARA ELE JÀ VOLTAR COMO LOGADO)
    public static void restartLogado() throws Exception {

        Controle controle = new Controle();
        controle.setLogado(true);
        controle.setTipoUser(tipoUser);
        main(null);
    }

}