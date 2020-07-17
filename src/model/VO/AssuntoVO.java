package model.VO;

public class AssuntoVO {
	
	private Long id;
	private String conteudo;
	private Long idDisciplina;
	
	public AssuntoVO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		String saida = "ID: " + id + "\n";
		saida += "Conteudo: " + conteudo + "\n";
		saida += "ID da disciplina: " + idDisciplina + "\n";
		return saida;
	}
}
