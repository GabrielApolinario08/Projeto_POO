package entity;

public class Adm extends Usuario{
    public Adm(int codigo, String nome, String email, String senha, String tipo) {
        super(codigo, nome, email, senha, tipo);
    }

    public String toString() {
        return super.toString();
    }
}
