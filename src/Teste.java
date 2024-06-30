import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Teste {
    public static void main(String[] args) throws IOException {
        int opc;
        do {
            try {
                opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione uma das opções:" +
                        "\n1) Entrar" +
                        "\n2) Cadastrar" +
                        "\n3) Sair" +
                        "\nInforme a opção:", "Menu", JOptionPane.QUESTION_MESSAGE));

                switch (opc) {
                    case 1:
                        //codigo
                    case 2:
                        //codigo
                }
            } catch (NumberFormatException e) {
                JOptionPane.showInternalMessageDialog(null,"Fim do programa!", null, JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } catch (NullPointerException e) {
                JOptionPane.showInternalMessageDialog(null,"Selecione uma opção válida.", "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        } while (true);


    }
}
//Caio Balieiro Mariano