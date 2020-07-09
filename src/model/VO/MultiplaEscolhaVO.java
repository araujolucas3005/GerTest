package model.VO;

public class MultiplaEscolhaVO extends ObjetivaVO {

	public void addOpcao(String opcao) {
		if (opcao != null) {
			// So eh possivel adicionar opcoes de 'a' ate 'z'
			if (super.getOpcoes().size() < 26) {
				char alternativa = 'a';
				alternativa += super.getOpcoes().size();
				String opcaoFormatada = String.valueOf(alternativa) + ") " + opcao;
				super.addOpcao(opcaoFormatada);
			}
		} 
	}

	public void removeOpcao(int posicao) {
		if (posicao < super.getOpcoes().size()) {
			
			// Tem que testar pra ver se a letra da opcao removido eh igual gabarito
			// caso for, o gabarito se torna invalido
			if (super.getGabarito().equals(String.valueOf(super.getOpcoes().get(posicao).charAt(0)))) {
				this.setGabarito(null);
			}
			
			super.removeOpcao(posicao);

			/*
			 * Eh necessario fazer com que as posicoes que se encontram apos a posicao
			 * removida tenham as suas alternativas subtraidas por 1
			 */
			for (int i = posicao; i < super.getOpcoes().size(); i++) {
				char alternativa = super.getOpcoes().get(i).charAt(0);
				alternativa--;
				String temp = super.getOpcoes().get(i).substring(3);
				super.getOpcoes().set(i, String.valueOf(alternativa) + ") " + temp);
			}
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
		while (teste == false && i < super.getOpcoes().size()) {

			/*
			 * Se o gabarito for igual a uma letra minuscula presente na primeira posicao de
			 * uma String opcao do array de opcoes o teste eh verdadeiro, logo o gabarito
			 * esta correto
			 */

			if (gabarito.equals(String.valueOf(super.getOpcoes().get(i).charAt(0)))) {
				teste = true;
			}
			i++;
		}
		return teste;
	}

}
