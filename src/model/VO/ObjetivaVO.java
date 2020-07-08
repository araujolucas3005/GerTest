package model.VO;

import java.util.Random;

public abstract class ObjetivaVO extends QuestaoVO {
	private String[] opcoes;
	
	public ObjetivaVO() {
		super();
		this.setOpcoes(null);
	}

	public String[] getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(String[] opcoesRecebidas) {
		this.opcoes = opcoesRecebidas;
	}

	public void setGabarito(String gabarito) {
		super.setGabarito(gabarito);
	}
	
	public void setCodigo(String codigo) {
		if (codigo != null && !codigo.isEmpty() && this.testeCodigoFormatoCorreto(codigo)) {
			super.setCodigo(codigo);
		} else {
			super.setCodigo(this.gerarCodigoAleatorio());
		}
	}
	
	//Todas os codigos das questoes objetivas devem comecar com O
	private boolean testeCodigoFormatoCorreto(String codigo) {
		if (codigo != null && codigo.length() == 5) {
			boolean testeFinal = true;
			boolean testeLetra = codigo.charAt(0) == 'O';
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
	
	//Gera um codigo aleatorio conforme o tipo da disciplina
	private String gerarCodigoAleatorio() {
		String codigoGerado;
		codigoGerado = "O";
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
		String saida = "----Questao Objetiva----";
		saida += super.toString();

		saida += "\nOpcoes:";
		for (int i = 0; i < this.opcoes.length; i++) {
			saida += "\n" + this.opcoes[i];
		}

		return saida;
	}
}
