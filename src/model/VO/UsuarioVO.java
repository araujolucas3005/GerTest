package model.VO;

public class UsuarioVO {
	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String email;
	private String cpf;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		if (id > 0)
			this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		if (login != null && login.length() >= 6)
			this.login = login;
		else
			this.login = "Invalido";		
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		if (senha != null && senha.length() >= 4)
			this.senha = senha;
		else
			this.senha = "Invalida";	
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome != null && nome != " ")
			this.nome = nome;
		else
			this.nome = "Invalido";	
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (email != null && email != " ")
			this.email = email;
		else
			this.email = "Invalido";	
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		if (cpf != null && cpf.length() >= 11)
			this.cpf = cpf;
		else
			this.cpf = "Invalido";	
	}
	
	public String toString() {
		String modeloString;
		modeloString = "----Usuario----";
		modeloString += "\nNome: " + this.nome;
		modeloString += "\nCpf: " + this.cpf;
		modeloString += "\nEmail: " + this.email;
		modeloString += "\nLogin: " + this.login;
		modeloString += "\nSenha: " + this.senha;
		return modeloString;

		}

}
