import entity.Controle;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        Controle controle = new Controle();
        int opc;
        String nome, email, senha, tipo;
        do {
            try {
            	Object[] optionsMenu = {"Entrar", "Cadastrar", "Sair"};
                opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Menu", 0, 3, null, optionsMenu, optionsMenu[0]);
                

                switch (opc) {
                    case 0:
                        controle.logar(entrada);
                        break;
                    case 1:
                        try {
                        	Object[] optionsCadastro = {"Cadastrar cliente", "Cadastrar profissional"};
                        	opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Cadastro", 0, 2, null, optionsCadastro, optionsCadastro[0]);
                      
                            switch (opc) {
                                case 0:
                                    controle.cadastrarCliente(entrada);
                                    break;
                                case 1:
                                    controle.cadastrarProfissional(entrada);
                                    break;
                                default:
                                    JOptionPane.showInternalMessageDialog(null,"Selecione uma opção válida.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showInternalMessageDialog(null,"Fim do programa!", null, JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 2:
                        JOptionPane.showInternalMessageDialog(null,"Fim do programa!", null, JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    default:
                        JOptionPane.showInternalMessageDialog(null,"Selecione uma opção válida.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showInternalMessageDialog(null,"Fim do programa!", null, JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);


    }
}
