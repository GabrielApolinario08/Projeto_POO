package main;

import entity.Adm;
import entity.Controle;

import javax.swing.*;
import java.io.*;
public class Main {
    static String tipoUser;
    static Controle controle = new Controle();
    public static void main(String[] args) throws Exception {
        restart();


    }

    public static void restart() throws Exception {


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

                    Object[] optionsADM = {"Cadastrar profissão","Mostrar profissões", "Remover Profissões","Cadastrar ADM", "Sair"};
                    while (true) {
                        opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Menu - ADM", 0, 5, null, optionsADM, optionsADM[0]);
                        switch (opc) {
                            case 0:
                                JTextField profissaoField = new JTextField();
                                Object[] opcoes = {"OK", "Voltar"};
                                int opt = JOptionPane.showOptionDialog(null, profissaoField, "Cadastro - Profissão", 0, 2, null, opcoes, opcoes[0]);
                                String service = profissaoField.getText();
                                adm.cadastrarProfissao(service, opt);
                                break;

                            case 1:


                            case 3:
                                controle.cadastrarAdm();
                                break;
                            case 4:
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



}