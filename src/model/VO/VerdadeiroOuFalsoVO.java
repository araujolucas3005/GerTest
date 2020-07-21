package model.VO;

public class VerdadeiroOuFalsoVO extends ObjetivaVO {

	// O gabarito deve ser uma String contendo V ou F sem espa√ßos
	// Exemplo: "VVVFFV"
	public void setGabarito(String gabarito) {
		int aux = gabarito.length();
		if (gabarito != null && !gabarito.isEmpty()) {
			for (int i = 0; i < aux; i++) {
				if (gabarito.charAt(i) == 'V' || gabarito.charAt(i) == 'F') {
					super.setGabarito(gabarito);
				}
				else {
				super.setGabarito("Gabarito inv·lido!");
				}
			}			
		}
		else {
			super.setGabarito("Questao sem gabarito!");
		}
	}	
}
