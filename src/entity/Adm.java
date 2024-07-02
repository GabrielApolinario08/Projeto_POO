package entity;

import java.io.*;


public class Adm extends Usuario {

    public Adm(int codigo, String nome, String email, String senha, String tipo) throws IOException {
        super(codigo, nome, email, senha, tipo);
    }

    public Adm() throws IOException {
        super();
    }

    public String toString() {
        return super.toString();
    }
}