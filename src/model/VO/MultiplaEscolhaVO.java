package model.VO;

public class MultiplaEscolhaVO extends ObjetivaVO {

	public void setGabarito(String gabarito) {
		if (gabarito != null && !gabarito.isEmpty()) {
			if (gabarito.length() < 2)
				super.setGabarito(gabarito);
			else super.setGabarito("Gabarito inválido");
		} else {
			super.setGabarito("Questao sem gabarito!");
		}
	}
}
