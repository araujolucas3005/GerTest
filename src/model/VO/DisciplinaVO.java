package model.VO;

import java.util.ArrayList;
import java.util.List;

public abstract class DisciplinaVO {
	private String nome;
	private String codigo;
	private List<String> assuntos = new ArrayList<String>();

	public DisciplinaVO() {
		this.setNome(null);
	}

	public DisciplinaVO(String nome, String codigo) {
		this.setNome(nome);
		this.setCodigo(codigo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			this.nome = "Disciplina sem nome";
		} else {
			this.nome = nome;
		}
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<String> getAssuntos() {
		return assuntos;
	}

	public void addAssunto(String assunto) {
		if (assunto != null && !assunto.isEmpty()) {
			assuntos.add(assunto);
		}
	}
	
	public void removeAssunto(int posicao) {
		if (posicao > 0 && posicao < this.assuntos.size()) {
			posicao--;
			assuntos.remove(posicao);
		}
	}

	public String toString() {
		String modeloString;
		modeloString = "----Disciplina----";
		modeloString += "\nNome: " + this.nome;
		modeloString += "\nCodigo: " + this.codigo;
		modeloString += "\nAssuntos: ";

		if (assuntos.size() == 0) {
			modeloString += "Sem assuntos ainda";
		} else {
			for (int i = 0; i < this.assuntos.size(); i++) {
				modeloString += "\n" + String.valueOf(i+1) + ". "+ assuntos.get(i);
			}
		}

		return modeloString;
	}
}
