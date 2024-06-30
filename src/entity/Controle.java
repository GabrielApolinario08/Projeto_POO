package entity;

import java.io.*;

public class Controle {
    private BufferedWriter usuariosDados;

    public Controle() {
        try {
            usuariosDados = new BufferedWriter(new FileWriter("usuarios.txt", true));
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    public void cadastrarCliente(Cliente cliente) throws IOException {
        usuariosDados.write(cliente.toString());
        usuariosDados.newLine();
    }
    public void cadastrarProfissional(Profissional profissional) throws IOException {
        usuariosDados.write(profissional.toString());
        usuariosDados.newLine();
    }



}
