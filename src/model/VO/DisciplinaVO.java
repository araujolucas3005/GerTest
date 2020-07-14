package model.VO;

import java.util.ArrayList;
import java.util.List;

public abstract class DisciplinaVO {
	private String nome;
	private String codigo;
	private List<AssuntoVO> assuntos = new ArrayList<AssuntoVO>();
	private List<QuestaoVO> questoes = new ArrayList<QuestaoVO>();
	private List<ProvaVO> provas = new ArrayList<ProvaVO>();

	public DisciplinaVO() {
		assuntos = new ArrayList<>();
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
	
	public List<ProvaVO> getProvas() {
		return provas;
	}
	
	public void addProva(ProvaVO prova) {
		if (prova != null) {
			provas.add(prova);
		}
	}
	
	public void removeProva(int idProva) {
		if (idProva > 0 && idProva <= provas.size()) {
			idProva--;
			provas.remove(idProva);
		}
	}
	
	public List<QuestaoVO> getQuestoes() {
		return questoes;
	}
	
	public void addQuestao(QuestaoVO questao) {
		if (questao != null) {
			questoes.add(questao);
		}
	}
	
	public void removeQuestao(int posicao) {
		if (posicao >= 0 && posicao <= provas.size()) {
			questoes.remove(posicao);
		}
	}
	
	public List<AssuntoVO> getAssuntos() {
		return assuntos;
	}

	public void addAssunto(AssuntoVO assunto) {
		if (assunto != null) {
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
			modeloString += "Sem assuntos ainda\n";
		} else {
			for (int i = 0; i < this.assuntos.size(); i++) {
				modeloString += "\n" + String.valueOf(i+1) + ". "+ assuntos.get(i);
			}
		}
		
		if (questoes.size() == 0) {
			modeloString += "Sem questoes ainda\n";
		} else {
			for (QuestaoVO questao : questoes) {
				modeloString += questao;
			}
		}
		
		if (provas.size() == 0) {
			modeloString += "Sem provas ainda\n";
		} else {
			for (ProvaVO prova : provas) {
				modeloString += prova;
			}
		}
		return modeloString;
	}
}
