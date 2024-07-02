package entity;

import java.io.IOException;

public class Profissao {

    private String name;
    protected int codigo;
    public Profissao() throws IOException {
        setName("Profissão");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IOException {
        if (name.isEmpty()) throw new IOException("Profissão não pode ser vazio!");
        this.name = name;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return getName();
    }
}