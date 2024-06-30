import javax.swing.*;

public class Teste {
    public static void main(String[] args) {
        JTextField nomeField = new JTextField();
        JTextField idadeField = new JTextField();

        Object[] message = {
                "Nome:", nomeField,
                "Idade:", idadeField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Informe seus dados", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            System.out.println("Nome: " + nome + ", Idade: " + idade);
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
