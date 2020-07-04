package model.VO;

import java.util.Random;

public class QuestaoVO {
	private DisciplinaVO disciplina;
	private int nivel;
	private String codigo;
	private String tipo;
	private String enunciado;
	private String gabarito;
	private String assunto;
	private String[] opcoes;

	public QuestaoVO() {

	}

	public QuestaoVO(DisciplinaVO disciplina, int nivel, String codigo, String tipo, String enunciado, String gabarito, String assunto, String[] opcoes) {
		setDiscip(disciplina);
		setNivel(nivel);
		setAssunto(assunto);
		setCodigo(codigo);
		setEnunciado(enunciado);
		setGabarito(gabarito);
		setTipo(tipo);
		setOpcoes(opcoes);
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

	public void setNivel(int nivel) {
		if (nivel < 0 && nivel >= 5)
			this.nivel = 0;
		else
			this.nivel = nivel;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (testeCodigoFormatoCorreto(codigo)) {
			this.codigo = codigo;
		} else {
			this.codigo = gerarCodigoAleatorio();
		}
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo == null || tipo.isEmpty())
			this.tipo = "Questao sem tipo";
		else {
			if (tipo != "Discursiva" || tipo != "Objetiva")
				this.tipo = "Tipo invalido";
			else
				this.tipo = tipo;
		}
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
		if (gabarito == null || gabarito.isEmpty())
			this.gabarito = "Questao sem gabarito";
		else
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

	public String[] getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(String[] opcoes) {
		if (tipo == "Objetiva") {
			if (opcoes == null) {
				this.opcoes = new String[1];
				this.opcoes[0] = "Questao sem opcoes!";
			} else {
				for (int i = 0; i < opcoes.length; i++) {
					if (opcoes[i] == null || opcoes[i].isEmpty()) {
						opcoes[i] = "Opcao nula";
					} else {
						this.opcoes[i] = opcoes[i];
					}
				}
			}
		} else {
			// por enquanto sera tratado desta maneira
			this.opcoes = new String[1];
			this.opcoes[0] = "Essa questao eh subjetiva!";
		}
	}

	private boolean testeCodigoFormatoCorreto(String codigo) {
		if (codigo != null && codigo.length() == 5) {
			boolean testeFinal = true;
			boolean testeLetra;
			if (tipo == "Objetiva") {
				testeLetra = codigo.charAt(0) == 'O';
			} else {
				testeLetra = codigo.charAt(0) == 'D';
			}
			if (testeLetra) {
				int i = 1;
				while (testeFinal && i < 5) {
					boolean testeNumero = Character.isDigit(codigo.charAt(i));
					if (testeNumero == false) {
						testeFinal = false;
					}
					++i;
				}
				return testeFinal;
			}
		}
		return false;
	}

	private String gerarCodigoAleatorio() {
		String codigoGerado;
		if (tipo == "Objetiva") {
			codigoGerado = "O";
		} else {
			codigoGerado = "D";
		}
		Random gerador = new Random();

		int numeroGerado = gerador.nextInt(10000);
		String numeroGeradoString;
		if (numeroGerado < 10) {
			numeroGeradoString = "000" + String.valueOf(numeroGerado);
		} else if (numeroGerado < 100) {
			numeroGeradoString = "00" + String.valueOf(numeroGerado);
		} else if (numeroGerado < 1000) {
			numeroGeradoString = "0" + String.valueOf(numeroGerado);
		} else {
			numeroGeradoString = String.valueOf(numeroGerado);
		}

		codigoGerado += numeroGeradoString;
		return codigoGerado;
	}

	public String toString() {
		String modeloString;
		modeloString = "----Questao----";
		modeloString = "\nDisciplina: " + this.disciplina;
		modeloString = "\nCodigo: " + this.codigo;
		modeloString = "\nNivel: " + this.nivel;
		modeloString = "\nAssunto: " + this.assunto;
		modeloString = "\nEnunciado: " + this.enunciado;
		modeloString = "\nGabarito: " + this.gabarito;
		modeloString = "\nOpcoes:\n";

		for (int i = 0; i < this.opcoes.length; i++) {
			modeloString += String.valueOf(i) + ". " + this.opcoes[i] + "\n";
		}

		return modeloString;

	}
}