public class Usuario {
	protected int codigo;
	protected String nome, email, senha, tipo;

	public Usuario() {
		this.codigo = 0;
		this.nome = "";
		this.email = "";
		this.senha = "";
		this.tipo = "";
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
