package model.VO;

import java.util.Random;

public class ObjetivaVO extends QuestaoVO {
	private String codigoTemp;
	private String[] opcoes;
	
	public ObjetivaVO() {
		super();
		this.setCodigo(null);
		this.setOpcoes(null);
	}

	public String[] getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(String[] opcoesRecebidas) {
		if (opcoesRecebidas != null) {
			this.opcoes = new String[opcoesRecebidas.length];
			char alternativa = 'a';

			// todas as opcoes sao do formato "letra) enunciado"
			for (int i = 0; i < opcoesRecebidas.length && alternativa <= 'z'; i++) {
				if (opcoesRecebidas[i] == null || opcoesRecebidas[i].isEmpty()) {
					this.opcoes[i] = "*Opcao invalida!*";
				} else {
					this.opcoes[i] = String.valueOf(alternativa) + ") " + opcoesRecebidas[i];
					alternativa++;
				}
			}
		} else {
			this.opcoes = new String[1];
			this.opcoes[0] = "Questao sem opcao";
		}
	}

	public void setGabarito(String gabarito) {
		if (gabarito != null && !gabarito.isEmpty()) {
			if (this.testeGabaritoValido(gabarito)) {
				super.setGabarito(gabarito);
			} else {
				super.setGabarito("Questao sem gabarito!");
			}
		} else {
			super.setGabarito("Questao sem gabarito!");
		}
	}
	
	public void setCodigo(String codigo) {
		if (codigo != null && !codigo.isEmpty() && this.testeCodigoFormatoCorreto(codigo)) {
			this.codigoTemp = codigo;
		} else {
			this.codigoTemp = this.gerarCodigoAleatorio();
		}
	}
	
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

	private boolean testeGabaritoValido(String gabarito) {
		boolean teste = false;
		int i = 0;
		while (teste == false && i < this.opcoes.length) {

			/*
			 * Se o gabarito for igual a uma letra minuscula presente na primeira posicao de
			 * uma String opcao do array de opcoes o teste eh verdadeiro, logo o gabarito
			 * esta correto
			 */

			if (gabarito.equals(String.valueOf(this.opcoes[i].charAt(0)))) {
				teste = true;
			}
			i++;
		}
		return teste;
	}

	public String toString() {
		String saida = "----Questao Objetiva----";
		saida += "\nCodigo: " + this.codigoTemp;
		saida += super.toString();

		saida += "\nOpcoes:\n";
		for (int i = 0; i < this.opcoes.length; i++) {
			saida += this.opcoes[i] + "\n";
		}

		return saida;
	}
}
