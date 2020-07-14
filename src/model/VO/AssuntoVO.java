package model.VO;

public class AssuntoVO {
	
	private String conteudo;
	private Long idDisciplina;
	
	public AssuntoVO() {
		
	}
	
	public AssuntoVO(String conteudo, Long id_disciplina) {
		this.conteudo = conteudo;
		this.idDisciplina = new Long(id_disciplina);
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
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
	public String toString() {
		return conteudo;
	}
}
