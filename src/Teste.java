import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) throws IOException {
        int opc = JOptionPane.showConfirmDialog(null, "oi", null, JOptionPane.ERROR_MESSAGE);
        System.out.println(opc);
    }
}
