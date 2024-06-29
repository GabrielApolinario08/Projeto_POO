import javax.swing.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
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
                case 2:

                case 3:
                    System.out.println("Fim do programa!");
                    return;
                default:
                    System.out.println("Selecione uma opção válida.");
            }
        } while (true);


    }
}
