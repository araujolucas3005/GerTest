package model.VO;

public class DiscursivaVO extends QuestaoVO {
	
	public void setGabarito(String gabarito) {
		if (gabarito != null && !gabarito.isEmpty()) {
			super.setGabarito(gabarito);
		} else {
			super.setGabarito("Questao sem gabarito!");
		}
	}
	
	public void setCodigo(String codigo) {
		if (codigo != null && !codigo.isEmpty() && super.testeCodigoFormatoCorreto(codigo, 'D')) {
			super.setCodigo(codigo);
		} else {
			super.setCodigo(super.gerarCodigoAleatorio('D'));
		}
	}
	
	public String toString() {
		String saida;
		saida = "----Questao Discursiva----";
		saida += super.toString();
		return saida;
	}
}
