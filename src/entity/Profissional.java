package entity;

public class Profissional extends Usuario{
    String profissao;
    public Profissional(int codigo, String nome, String email, String senha, String tipo, String profissao) {
        super(codigo, nome, email, senha, tipo);
        profissao = profissao;
    }

    @Override
    public String toString() {
        return super.toString() + profissao;
    }
}
