package entity;

import java.util.logging.Logger;

public class Cliente extends Usuario{

    public Cliente(int codigo, String nome, String email, String senha, String tipo) {
        super(codigo, nome, email, senha, tipo);
    }
    public Cliente() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}