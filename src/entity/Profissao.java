package entity;

public class Profissao {

    private String name;
    public Profissao(){
        setName("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
