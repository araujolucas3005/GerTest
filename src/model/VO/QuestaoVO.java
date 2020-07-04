package model.VO;
import java.util.Random;

/* Ainda nao foi utilizada herança para criar as possíveis subclasses DiscursivaVO e ObjetivaVO. 
 * Logo, uma questao discursiva, por enquanto, possui o metodo setOpcao() e getOpcao() e a variavel opcao.
 * Aguardando o andamento da disciplina para refatorar... */

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
		this.setDiscip(disciplina);
		this.setNivel(nivel);
		this.setAssunto(assunto);
		this.setCodigo(codigo);
		this.setEnunciado(enunciado);
		this.setGabarito(gabarito);
		this.setTipo(tipo);
		this.setOpcoes(opcoes);
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
			if (tipo.equals("Discursiva") || tipo.equals("Objetiva"))
				this.tipo = tipo;
			else
				this.tipo = "Tipo invalido!";
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
		if (gabarito != null && !gabarito.isEmpty()) {
			if (tipo.equals("Objetiva")) {
				if (this.testeGabaritoValido(gabarito)) {
					this.gabarito = gabarito;
				} else {
					this.gabarito = "Questao sem gabarito!";
				}
			} else {
				this.gabarito = gabarito;
			}
		} else {
			this.gabarito = "Questao sem gabarito!";
		}
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

	public void setOpcoes(String[] opcoesRecebidas) {
		if (tipo.equals("Objetiva")) {
			if (opcoesRecebidas != null) {
				this.opcoes = new String[opcoesRecebidas.length];
				char alternativa = 'a';

				// todas as opcoes sao do formato "letra) enunciado"
				for (int i = 0; i < opcoesRecebidas.length && alternativa <= 'z'; i++) {
					if (opcoesRecebidas[i] == null || opcoesRecebidas[i].isEmpty()) {
						this.opcoes[i] = "Opcao invalida!";
					} else {
						this.opcoes[i] = String.valueOf(alternativa) + ") " + opcoesRecebidas[i];
						alternativa++;
					}
				}
			} else {
				this.opcoes = new String[1];
				this.opcoes[0] = "Questao sem opcao";
			}
		} else {
			this.opcoes = new String[1];
			this.opcoes[0] = "Essa Questao eh subjetiva!";
		}
	}

	/* Eh preciso testar se o codigo de uma discursiva começa com D e de uma objetiva comeca com O
	 * e se sao numeros os elementos após */
	private boolean testeCodigoFormatoCorreto(String codigo) {
		if (codigo != null && codigo.length() == 5) {
			boolean testeFinal = true;
			boolean testeLetra;
			if (tipo.equals("Objetiva")) {
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
		if (tipo.equals("Objetiva")) {
			codigoGerado = "O";
		} else if (tipo.equals("Discursiva")) {
			codigoGerado = "D";
		} else {
			codigoGerado = "TIPO_NAO_DECLARADO_OU_INVALIDO";
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

	private boolean testeGabaritoValido(String gabarito) {
		boolean teste = false;
		int i = 0;
		while (teste == false && i < this.opcoes.length) {

			/* Se o gabarito for igual a uma letra minuscula presente na primeira posicao de
			 * uma String opcao do array de opcoes o teste eh verdadeiro, logo o gabarito
			 * esta correto */
			
			if (gabarito.equals(String.valueOf(this.opcoes[i].charAt(0)))) {
				teste = true;
			}
			i++;
		}
		return teste;
	}

	public String toString() {
		String modeloString;
		modeloString = "----Questao----";
		modeloString += "\nDisciplina: " + this.disciplina.getNome();
		modeloString += "\nCodigo: " + this.codigo;
		modeloString += "\nTipo: " + this.tipo;
		modeloString += "\nNivel: " + this.nivel;
		modeloString += "\nAssunto: " + this.assunto;
		modeloString += "\nEnunciado: " + this.enunciado;
		modeloString += "\nGabarito: " + this.gabarito;

		if (this.opcoes != null && tipo.equals("Objetiva")) {
			modeloString += "\nOpcoes:\n";

			for (int i = 0; i < this.opcoes.length; i++) {
				modeloString += this.opcoes[i] + "\n";
			}
		}

		return modeloString;

	}
}