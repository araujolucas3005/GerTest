package model.VO;

public abstract class DisciplinaVO {
	private String nome;
	private String[] assuntos;

	public DisciplinaVO() {
		this.setNome(null);
		this.setAssuntos(null);
	}

	public DisciplinaVO(String nome, String codigo, String[] assuntos) {
		this.setNome(nome);
		this.setAssuntos(assuntos);
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

	public abstract String getCodigo();

	public abstract void setCodigo(String codigo);

	public String[] getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(String[] assuntos) {
		if (assuntos != null) {
			for (int i = 0; i < assuntos.length; i++) {
				if (assuntos[i] == null || assuntos[i].isEmpty()) {
					assuntos[i] = "Sem assunto";
				}
			}
			this.assuntos = assuntos;
		} else {
			this.assuntos = new String[1];
			this.assuntos[0] = "Disciplina sem assunto";
		}
	}

	public String toString() {
		String modeloString;
		modeloString = "\nNome: " + this.nome;
		modeloString += "\nAssuntos: \n";

		for (int i = 0; i < this.assuntos.length; i++) {
			modeloString += String.valueOf(i + 1) + ". " + this.assuntos[i];
		}

		return modeloString;
	}
}
