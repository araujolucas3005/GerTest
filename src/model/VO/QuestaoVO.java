package model.VO;

public abstract class QuestaoVO {
	
	private int nivel;
	private String codigo;
	private String enunciado;
	private String gabarito;
	private Long idDisciplina;
	private Long idAssunto;
	private Long idQuestao;
	private String tipo;

	public QuestaoVO() {

	}

	public QuestaoVO(String codigo, int nivel, String tipo, String enunciado, String gabarito,
			String assunto) {
		this.setCodigo(codigo);
		this.setNivel(nivel);
		this.setEnunciado(enunciado);
		this.setGabarito(gabarito);
	}
	
	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}
	
	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public Long getIdAssunto() {
		return idAssunto;
	}

	public void setIdAssunto(Long idAssunto) {
		this.idAssunto = idAssunto;
	}

	public int getNivel() {
		return nivel;
	}

	// Nivel vai apenas de 1 a 4
	public void setNivel(int nivel) {
		if (nivel > 0 && nivel < 5) {
			this.nivel = nivel;
		} else {
			this.nivel = 0;
		}
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		if (enunciado == null || enunciado.isEmpty())
			this.enunciado = "Questao sem enunciado";
		else
			this.enunciado = enunciado;
	}

	public String getGabarito() {
		return gabarito;
	}

	public void setGabarito(String gabarito) {
		if (gabarito == null || gabarito.isEmpty()) {
			this.gabarito = "Questao sem gabarito";
		}
		else {
			this.gabarito = gabarito;
		}			
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo == null || tipo.isEmpty())
			this.enunciado = "Questao sem tipo definido!";
		else
			this.tipo = tipo;
	}

	public String toString() {
		String modeloString;
		modeloString = "\nCodigo: " + this.codigo;
		modeloString += "\nNivel: " + this.nivel;
		modeloString += "\nEnunciado: " + this.enunciado;
		modeloString += "\nGabarito: " + this.gabarito;

		return modeloString;

	}
}