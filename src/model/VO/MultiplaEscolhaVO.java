package model.VO;

public class MultiplaEscolhaVO extends ObjetivaVO {
	
	public void setOpcoes(String[] opcoesRecebidas) {
		String[] opcoes;
		if (opcoesRecebidas != null) {
			opcoes = new String[opcoesRecebidas.length];
			char alternativa = 'a';

			// todas as opcoes sao do formato "letra) enunciado"
			for (int i = 0; i < opcoesRecebidas.length && alternativa <= 'z'; i++) {
				if (opcoesRecebidas[i] == null || opcoesRecebidas[i].isEmpty()) {
					opcoes[i] = "*Opcao invalida!*";
				} else {
					opcoes[i] = String.valueOf(alternativa) + ") " + opcoesRecebidas[i];
					alternativa++;
				}
			}
			super.setOpcoes(opcoes);
		} else {
			opcoes = new String[1];
			opcoes[0] = "Questao sem opcoes!";
			super.setOpcoes(opcoes);
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
	
	private boolean testeGabaritoValido(String gabarito) {
		boolean teste = false;
		int i = 0;
		while (teste == false && i < super.getOpcoes().length) {

			/*
			 * Se o gabarito for igual a uma letra minuscula presente na primeira posicao de
			 * uma String opcao do array de opcoes o teste eh verdadeiro, logo o gabarito
			 * esta correto
			 */

			if (gabarito.equals(String.valueOf(super.getOpcoes()[i].charAt(0)))) {
				teste = true;
			}
			i++;
		}
		return teste;
	}
	
	
}
