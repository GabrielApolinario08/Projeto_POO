import entity.Controle;

import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Controle controle = new Controle();
        int opc;

        do {
            System.out.println("------- Menu ---------");
            System.out.println("Selecione as opções:");
            System.out.println("1) Entrar");
            System.out.println("2) Cadastrar");
            System.out.println("3) Sair");
            System.out.print("Informe a opção:");
            opc = entrada.nextInt();

            switch (opc) {
                case 1:
                case 2:
                    System.out.println("Selecione como deseja se cadastrar:");
                    System.out.println("1) Usuário");
                    System.out.println("2) Profissional");
                    controle.cadastrar(entrada.nextInt());
                case 3:
                    System.out.println("Fim do programa!");
                    return;
                default:
                    System.out.println("Selecione uma opção válida.");
            }
        } while (true);


    }
}
