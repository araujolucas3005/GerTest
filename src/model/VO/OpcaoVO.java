package model.VO;

public class OpcaoVO {
	
	private String conteudo;
	private Long idQuestao;
	private Long id;

	public OpcaoVO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public OpcaoVO(String conteudo, Long idQuestao) {
		this.conteudo = conteudo;
		this.idQuestao = new Long(idQuestao);
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		if (conteudo != null && !conteudo.isEmpty()) {
			this.conteudo = conteudo;
		}
	}

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}
	
	public String toString() {
		return conteudo;
	}
}
