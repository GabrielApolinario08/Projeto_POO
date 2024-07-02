import entity.Controle;

import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Controle controle = new Controle();
        int opc;
        
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog("------- Menu ---------" 
            + "\nSelecione as opções:"
            + "\n1) Entrar"
            + "\n2) Cadastrar"
            + "\n3) Sair"
            + "\nInforme a opção:"));
 

            switch (opc) {
                case 1:
                case 2:
                   01
                    controle.cadastrar(Integer.parseInt(JOptionPane.showInputDialog("Selecione como deseja se cadastrar:"
                    		+ "\n1) Usuário"
                    		+ "\n2) Profissional")));
                case 3:
                    System.out.println("Fim do programa!");
                    return;
                default:
                    System.out.println("Selecione uma opção válida.");
            }
        } while (true);


    }
}
