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

        if (nomeField.getText().length() < 4) {
            System.out.println("po");
        }
    }
}
