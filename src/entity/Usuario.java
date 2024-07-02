package entity;

import java.io.IOException;

public class Usuario {
	protected int codigo;
	protected String nome, email, senha, tipo;

	public Usuario() throws IOException {
		setCodigo(0);
		setNome("Usuario");
		setEmail("Usuario@");
		setSenha("usuario1234");
		setTipo("Cliente");
	}

	public Usuario(int codigo, String nome, String email, String senha, String tipo) throws IOException {
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

	public void setNome(String nome) throws IOException {
		if (nome.length() < 2) {
			throw new IOException("Nome precisa ter no mínimo 2 caracteres");
		} else {
			this.nome = nome;
		}
	}
	public String getNome() {
		return nome;
	}

	public void setEmail(String email) throws IOException {
		if (!email.contains("@")) {
			throw new IOException("Email inválido");
		} else {
			this.email = email;
		}
	}
	public String getEmail() {
		return email;
	}

	public void setSenha(String senha) throws IOException {
		if (senha.length() < 8) {
			throw new IOException("Senha precisa ter no mínimo 8 caracteres");
		} else {
			this.senha = senha;
		}
	}
	public String getSenha() {
		return senha;
	}

	public void setTipo(String tipo) throws IOException {
		if (tipo.equals("Cliente") || tipo.equals("Profissional") || tipo.equals("ADM")) {
			this.tipo = tipo;
		} else {
			throw new IOException("Tipo de usuário inválido");
		}
	}
	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return tipo + ";" + codigo + ";" + nome + ";" + email + ";" + senha;
	}
}