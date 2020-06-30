package model.VO;
import java.util.Random;

public class DisciplinaVO {
	private String nome;
	private String codigo;
	private String[] assuntos;
	
	public DisciplinaVO(String nome, String codigo, String[] assuntos) {
		this.setNome(nome);
		this.setCodigo(codigo);
		this.setAssuntos(assuntos);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.isEmpty()) {
			this.nome = "Disciplina sem nome";
		} else {
			this.nome = nome;
		}
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		if (codigo.charAt(0) == 'Q' || codigo.length() == 6) {
			this.codigo = codigo;
		} else {
			Random gerador = new Random();
			this.codigo = "Q" + String.valueOf(gerador.nextInt(1000000));
		}
	}
	
	public String[] getAssuntos() {
		return assuntos;
	}
	
	public void setAssuntos(String[] assuntos) {
		for (int i = 0; i < assuntos.length; i++) {
			if (assuntos[i].isEmpty()) {
				assuntos[i] = "Sem assunto";
			}
		}
		this.assuntos = assuntos;
	}
}
