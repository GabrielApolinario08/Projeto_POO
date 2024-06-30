package entity;

public class Profissional extends Usuario{
    public Profissional(Long codigo, String nome, String email, String senha, String tipo) {
        super(codigo, nome, email, senha, tipo);
    }
}