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
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Fim do programa!");
                    return;
                default:
                    System.out.println("Selecione uma opção válida.");
            }
        } while (true);

    }
}
