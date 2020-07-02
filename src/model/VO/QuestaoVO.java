package model.VO;
import java.util.Random;

public class QuestaoVO {
	private String discip;
	private int nivel;
	private String codigo;
	private String tipo;
	private String enunciado;
	private String gabarito;
	private String assunto;
	private String[] opcoes;

	public QuestaoVO() {

	}

	public QuestaoVO(String discip, int nivel, String codigo, String tipo, String enunciado, String gabarito,
			String assunto, String[] opcoes) {
		this.setDiscip(discip);
		this.setNivel(nivel);
		this.setAssunto(assunto);
		this.setCodigo(codigo);
		this.setEnunciado(enunciado);
		this.setGabarito(gabarito);
		this.setTipo(tipo);
		this.setOpcoes(opcoes);
	}

	public String getDiscip() {
		return discip;
	}

	public void setDiscip(String discip) {
		if ((discip.isEmpty()))
			this.discip = "Quest�o sem disciplina";
		else
			this.discip = discip;
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
		if (tipo.isEmpty())
			this.tipo = "Quest�o sem tipo";
		else
			this.tipo = tipo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		if (enunciado.isEmpty())
			this.enunciado = "Quest�o sem enunciado";
		else
			this.enunciado = enunciado;
	}

	public String getGabarito() {
		return gabarito;
	}

	public void setGabarito(String gabarito) {
		if (gabarito.isEmpty())
			this.gabarito = "Quest�o sem gabarito";
		else
			this.gabarito = gabarito;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		if (assunto.isEmpty())
			this.assunto = "Quest�o sem assunto";
		else
			this.assunto = assunto;
	}

	public String[] getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(String[] opcoes) {
		for (int i = 0; i < opcoes.length; i++) {
			if (opcoes[i].isEmpty()) {
				opcoes[i] = "Sem op��o";
			} else
				this.opcoes[i] = opcoes[i];
		}
	}

	private boolean testeCodigoFormatoCorreto(String codigo) {
		if (codigo != null && codigo.length() == 5) {
			boolean testeFinal = true;
			boolean testeLetra = codigo.charAt(0) == 'Q';
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
		String codigoGerado = "Q";
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
		modeloString = "----Quest�o----" + "\nDisciplina: " + this.discip + "\nCodigo: " + this.codigo + "\nNivel: "
				+ this.nivel + "\nAssunto: " + this.assunto + "\nEnunciado: " + this.enunciado + "\nGabarito: "
				+ this.gabarito + "\n----Op��es----\n";

		for (int i = 0; i < this.opcoes.length; i++) {
			modeloString += String.valueOf(i) + ". " + this.opcoes[i] + "\n";
		}

		return modeloString;

	}
}