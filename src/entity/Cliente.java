package entity;

import java.io.IOException;
import java.util.logging.Logger;

public class Cliente extends Usuario{

    public Cliente(int codigo, String nome, String email, String senha, String tipo) throws IOException {
        super(codigo, nome, email, senha, tipo);
    }
    public Cliente() throws IOException {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}