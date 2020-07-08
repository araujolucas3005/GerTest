package model.VO;

public abstract class QuestaoVO {
	
	private DisciplinaVO disciplina;
	private int nivel;
	private String codigo;
	private String enunciado;
	private String gabarito;
	private String assunto;

	public QuestaoVO() {
		this.setDiscip(null);
		this.setCodigo(null);
		this.setNivel(0);
		this.setAssunto(null);
		this.setEnunciado(null);
		this.setGabarito(null);
	}

	public QuestaoVO(DisciplinaVO disciplina, int nivel, String codigo, String tipo, String enunciado, String gabarito,
			String assunto, String[] opcoes) {
		this.setDiscip(disciplina);
		this.setNivel(nivel);
		this.setAssunto(assunto);
		this.setEnunciado(enunciado);
		this.setGabarito(gabarito);
	}

	public DisciplinaVO getDiscip() {
		return this.disciplina;
	}

	public void setDiscip(DisciplinaVO disciplina) {
		if (disciplina != null) {
			this.disciplina = disciplina;
		} else {
			this.disciplina = new DisciplinaVO();
		}
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
		if (assunto != null && !assunto.isEmpty() && testeDisciplinaContemAssunto(assunto)) {
			this.assunto = assunto;
		} else {
			this.assunto = "Questao sem assunto!";
		}
	}

	private boolean testeDisciplinaContemAssunto(String assunto) {
		boolean teste = false;
		int i = 0;
		String[] assuntosDisciplina = this.disciplina.getAssuntos();
		while (teste == false && i < assuntosDisciplina.length) {
			if (assunto.equals(assuntosDisciplina[i])) {
				teste = true;
			}
			i++;
		}
		return teste;
	}

	public String toString() {
		String modeloString;
		modeloString = "\nDisciplina: " + this.disciplina.getNome();
		modeloString += "\nNivel: " + this.nivel;
		modeloString += "\nCodigo: " + this.codigo;
		modeloString += "\nAssunto: " + this.assunto;
		modeloString += "\nEnunciado: " + this.enunciado;
		modeloString += "\nGabarito: " + this.gabarito;

		return modeloString;

	}
}