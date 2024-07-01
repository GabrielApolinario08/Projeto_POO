import entity.Adm;
import entity.Cliente;
import entity.Controle;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        Controle controle = new Controle();
        Adm adm = new Adm(01, "pedro", "pedro@", "8888888888", "Adm");
        int opc;
        String nome, email, senha, tipo;
        do {
            if (!controle.isLogado()){
                try {
                    Object[] optionsMenu = {"Entrar", "Cadastrar", "Sair"};
                    opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Menu", 0, 3, null, optionsMenu, optionsMenu[0]);


                    switch (opc) {
                        case 0:
                            controle.logar(entrada);
                            controle.setLogado(true);
                            break;
                        case 1:
                            try {
                                Object[] optionsCadastro = {"Cadastrar cliente", "Cadastrar profissional"};
                                opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Cadastro - usuários", 0, 2, null, optionsCadastro, optionsCadastro[0]);

                                switch (opc) {
                                    case 0:
                                        controle.cadastrarCliente();
                                        break;
                                    case 1:
                                        controle.cadastrarProfissional();
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
                    Object[] optionsCadastroProfissao = {"Cadastrar profissão", "Sair"};
                    opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Menu - ADM", 0, 2, null, optionsCadastroProfissao, optionsCadastroProfissao[0]);
                    switch (opc) {
                        case 0:
                            adm.cadastrarProfissao(JOptionPane.showInputDialog(null, "Informe o nome da profissão:", "Cadastro - Profissão", JOptionPane.QUESTION_MESSAGE));
                            break;
                        case 1:
                            adm.arquivar();
                            JOptionPane.showMessageDialog(null, "Profissões cadastradas com sucesso.\nFim do programa", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        default:
                            JOptionPane.showInternalMessageDialog(null,"Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                            break;
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
                break;
            }
        } while (true);
    }
}