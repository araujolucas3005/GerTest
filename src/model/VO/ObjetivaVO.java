package model.VO;

public class ObjetivaVO extends QuestaoVO {
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
		super.setCodigo(codigo, 'O');
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
		saida += super.toString();

		saida += "\nOpcoes:\n";
		for (int i = 0; i < this.opcoes.length; i++) {
			saida += this.opcoes[i] + "\n";
		}

		return saida;
	}
}
