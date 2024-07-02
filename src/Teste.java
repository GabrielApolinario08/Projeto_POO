import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);

        BufferedWriter profissoesTxt = new BufferedWriter(new FileWriter("arquivo.txt", true));
        while (true) {
            String servico = entrada.nextLine();
            if (servico.length() < 2) {
                System.out.println("Profissão deve possuir no mínimo 2 caracteres");
            } else {
                profissoesTxt.write(servico);
                profissoesTxt.newLine();
                profissoesTxt.close();
            }
        }
    }
}
