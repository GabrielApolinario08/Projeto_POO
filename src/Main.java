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
                opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione uma das opções:" +
                        "\n1) Entrar" +
                        "\n2) Cadastrar" +
                        "\n3) Sair" +
                        "\nInforme a opção:", "Menu", JOptionPane.QUESTION_MESSAGE));

                switch (opc) {
                    case 1:
                        controle.logar(entrada);
                        break;
                    case 2:
                        try {
                            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione uma das opções:" +
                                    "\n1) Cadastrar cliente" +
                                    "\n2) Cadastrar profissional" +
                                    "\nInforme a opção:", "Menu - cadastrar", JOptionPane.QUESTION_MESSAGE));
                            switch (opc) {
                                case 1:
                                    controle.cadastrarCliente(entrada);
                                    break;
                                case 2:
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
                    case 3:
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
