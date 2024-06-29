package entity;

import java.util.logging.Logger;

public class Cliente extends Usuario{

    public Cliente(Long codigo, String nome, String email, String senha, String tipo) {
        super(codigo, nome, email, senha, tipo);
    }
}
