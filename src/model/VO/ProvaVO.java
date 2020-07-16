package model.VO;

import java.util.List;

public class ProvaVO {
	private Long id;
	private Long idDisciplina;
	private List<QuestaoVO> questoes;
	private int nivel1;
	private int nivel2;
	private int nivel3;
	private int nivel4;

	public ProvaVO() {

	}

	public ProvaVO(List<QuestaoVO> questoes,int nivel1, int nivel2, int nivel3, int nivel4) {
		this.questoes = questoes;
		this.nivel1 = nivel1;
		this.nivel2 = nivel2;
		this.nivel3 = nivel3;
		this.nivel4 = nivel4;
	}
	
	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// questoes sao geradas de maneira aleatoria
	public List<QuestaoVO> getQuestoes() {
		return questoes;
	}
	
	public void addQuestao(QuestaoVO questao) {
		if (questao != null) {
			questoes.add(questao);
		}
	}
	
	public void removeQuestao(int posicaoQuestao) {
		if (posicaoQuestao >= 0 && posicaoQuestao < questoes.size()) {
			questoes.remove(posicaoQuestao);
		}
	}

	public int getNivel1() {
		return nivel1;
	}

	public void setNivel1(int nivel1) {
		if (nivel1 <= 0)
			this.nivel1 = 1;
		else
			this.nivel1 = nivel1;
	}

	public int getNivel2() {
		return nivel2;
	}

	public void setNivel2(int nivel2) {
		if (nivel2 <= 0)
			this.nivel2 = 1;
		else
			this.nivel2 = nivel2;
	}

	public int getNivel3() {
		return nivel3;
	}

	public void setNivel3(int nivel3) {
		if (nivel3 <= 0)
			this.nivel3 = 1;
		else
			this.nivel3 = nivel3;
	}

	public int getNivel4() {
		return nivel4;
	}

	public void setNivel4(int nivel4) {
		if (nivel4 <= 0)
			this.nivel4 = 1;
		else
			this.nivel4 = nivel4;
	}

	public String toString() {
		String modeloString;
		modeloString = "----Prova----";
		modeloString += "\n----Questoes----\n";

		for (int i = 0; i < this.questoes.size(); i++) {
			modeloString += String.valueOf(i) + ". " + this.questoes.get(i) + "\n";
		}

		return modeloString;

	}
}