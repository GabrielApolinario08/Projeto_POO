package entity;

public class Usuario {
	protected int codigo;
	protected String nome, email, senha, tipo;

	public Usuario() {
		setCodigo(0);
		setNome("");
		setEmail("");
		setSenha("");
		setTipo("");
	}

	public Usuario(int codigo, String nome, String email, String senha, String tipo) {
		setCodigo(codigo);
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setTipo(tipo);
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha() {
		return senha;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
}
