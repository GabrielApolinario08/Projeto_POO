package entity;

public class Profissional extends Usuario{
<<<<<<< HEAD
    String profissao;
    public Profissional(int codigo, String nome, String email, String senha, String tipo, String profissao) {
=======
    public Profissional(int codigo, String nome, String email, String senha, String tipo) {
>>>>>>> ee53bd30f6d40f4a6a1791cb197873e51b7c2c94
        super(codigo, nome, email, senha, tipo);
        setProfissao(profissao);
    }

    public Profissional() {

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