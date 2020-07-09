package model.VO;

public class VerdadeiroOuFalsoVO extends ObjetivaVO {
	// Qualquer opcao eh valida, desde que esta nao seja null e nao esteja em branco
	public void addOpcao(String opcao) {
		if (opcao != null && !opcao.isEmpty()) {
			super.addOpcao(opcao);
		} 
	}
	
	public void removeOpcao(int posicao) {
		if (posicao > 0) {
			super.setGabarito("renove o gabarito!");
			super.removeOpcao(posicao);
		}
	}

	// O gabarito deve ser uma String contendo V ou F sem espaços
	// Exemplo: "VVVFFV"
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

	// O tamanho da String deve ter o mesmo tamanho da quantidade de opcoes
	private boolean testeGabaritoValido(String gabarito) {
		if (gabarito.length() == super.getOpcoes().size()) {
			for (int i = 0; i < gabarito.length(); i++) {
				char letra = gabarito.charAt(i);
				if (letra != 'V' && letra != 'F') {
						return false;
					}
				}
			return true;
		}
		return false;
	}
}
