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
                    opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione uma das opções:" +
                    "\n1) Cadastrar cliente" +
                    "\n2) Cadastrar profissional" +
                    "\nInforme a opção:", "Menu - cadastrar", JOptionPane.QUESTION_MESSAGE));
                    switch (opc) {
                        case 1:
                            System.out.println("Cadastrar cliente:");
                            System.out.print("Nome: ");
                            nome = entrada.nextLine();
                            System.out.print("Email: ");
                            email = entrada.nextLine();
                            while (true) {
                                System.out.print("Senha: ");
                                senha = entrada.nextLine();
                                if (senha.length() < 8) {
                                    System.out.println("Senha precisa ser maior que 8 caracteres.");
                                } else {
                                    return;
                                }
                            }
                    }

                case 3:
                    System.out.println("Fim do programa!");
                    return;
                default:
                    System.out.println("Selecione uma opção válida.");
            }
        } while (true);


    }
}
