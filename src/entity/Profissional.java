package entity;

import java.io.IOException;

public class Profissional extends Usuario{
    String profissao;
    public Profissional(int codigo, String nome, String email, String senha, String tipo, String profissao) throws IOException {
        super(codigo, nome, email, senha, tipo);
        setProfissao(profissao);
    }

    public Profissional() throws IOException {
        super();
        setProfissao("");
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + profissao;
    }
}