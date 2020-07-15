package model.VO;

public abstract class QuestaoVO {
	
	private Long idQuestao;
	private int nivel;
	private String codigo;
	private String enunciado;
	private String gabarito;
	private String assunto;
	private Long idDisciplina;
	private Long idAssuntos;
	private String tipo;

	public QuestaoVO() {

	}

	public QuestaoVO(String codigo, int nivel, String tipo, String enunciado, String gabarito,
			String assunto) {
		this.setCodigo(codigo);
		this.setNivel(nivel);
		this.setAssunto(assunto);
		this.setEnunciado(enunciado);
		this.setGabarito(gabarito);
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
		this.gabarito = gabarito;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		if (assunto != null && !assunto.isEmpty()) {
			this.assunto = assunto;
		} else {
			this.assunto = "Questao sem assunto!";
		}
	}

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long id) {
		this.idQuestao = id;
	}

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public Long getIdAssuntos() {
		return idAssuntos;
	}

	public void setIdAssuntos(Long idAssuntos) {
		this.idAssuntos = idAssuntos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
			this.tipo = tipo;
	}

	public String toString() {
		String modeloString;
		modeloString = "\nCodigo: " + this.codigo;
		modeloString += "\nNivel: " + this.nivel;
		modeloString += "\nAssunto: " + this.assunto;
		modeloString += "\nEnunciado: " + this.enunciado;
		modeloString += "\nGabarito: " + this.gabarito;

		return modeloString;

	}

}