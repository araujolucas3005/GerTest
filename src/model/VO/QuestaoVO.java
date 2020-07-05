package model.VO;

import java.util.Random;

/* Ainda nao foi utilizada herança para criar as possíveis subclasses DiscursivaVO e ObjetivaVO. 
 * Logo, uma questao discursiva, por enquanto, possui o metodo setOpcao() e getOpcao() e a variavel opcao.
 * Aguardando o andamento da disciplina para refatorar... */

public class QuestaoVO {
	private DisciplinaVO disciplina;
	private int nivel;
	private String codigo;
	private String enunciado;
	private String gabarito;
	private String assunto;

	public QuestaoVO() {
		this.setDiscip(null);
		this.setNivel(0);
		this.setAssunto(null);
		this.setCodigo(null);
		this.setEnunciado(null);
		this.setGabarito(null);
	}

	public QuestaoVO(DisciplinaVO disciplina, int nivel, String codigo, String tipo, String enunciado, String gabarito,
			String assunto, String[] opcoes) {
		this.setDiscip(disciplina);
		this.setNivel(nivel);
		this.setAssunto(assunto);
		this.setCodigo(codigo);
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
		return codigo;
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

	/*
	 * Eh preciso testar se o codigo de uma discursiva começa com D e de uma
	 * objetiva comeca com O e se sao numeros os elementos após
	 */
	protected boolean testeCodigoFormatoCorreto(String codigo, char letraTipoQuestao) {
		if (codigo != null && codigo.length() == 5) {
			boolean testeFinal = true;
			boolean testeLetra = codigo.charAt(0) == letraTipoQuestao;
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

	protected String gerarCodigoAleatorio(char letraQuestao) {
		String codigoGerado;
		codigoGerado = String.valueOf(letraQuestao);
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
		modeloString += "\nCodigo: " + this.codigo;
		modeloString += "\nNivel: " + this.nivel;
		modeloString += "\nAssunto: " + this.assunto;
		modeloString += "\nEnunciado: " + this.enunciado;
		modeloString += "\nGabarito: " + this.gabarito;

		return modeloString;

	}
}