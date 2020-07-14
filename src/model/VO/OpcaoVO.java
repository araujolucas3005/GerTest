package model.VO;

public class OpcaoVO {
	
	private String conteudo;
	private Long idQuestao;
	
	public OpcaoVO() {
		
	}
	
	public OpcaoVO(String conteudo, Long id_disciplina) {
		this.conteudo = conteudo;
		this.idQuestao = new Long(id_disciplina);
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		if (conteudo != null && !conteudo.isEmpty()) {
			this.conteudo = conteudo;
		}
	}

	public Long getIdDisciplina() {
		return idQuestao;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idQuestao = idDisciplina;
	}
	
	public String toString() {
		return conteudo;
	}
}
