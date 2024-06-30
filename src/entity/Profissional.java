package entity;

public class Profissional extends Usuario{
    Profissao profissao;
    public Profissional(int codigo, String nome, String email, String senha, String tipo) {
        super(codigo, nome, email, senha, tipo);
    }

    @Override
    public String toString() {
        return super.toString() + profissao;
    }
}
