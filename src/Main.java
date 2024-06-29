import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opc;
        String nome, email, senha, tipo;

        do {
            System.out.println("------- Menu ---------");
            System.out.println("Selecione as opções:");
            System.out.println("1) Entrar");
            System.out.println("2) Cadastrar");
            System.out.println("3) Sair");
            System.out.print("Informe a opção: ");
            opc = entrada.nextInt();

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
