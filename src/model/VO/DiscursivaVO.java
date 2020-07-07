package model.VO;

public class DiscursivaVO extends QuestaoVO {
	
	public DiscursivaVO() {
		super();
		this.setCodigo(null);
	}
	
	public void setGabarito(String gabarito) {
		if (gabarito != null && !gabarito.isEmpty()) {
			super.setGabarito(gabarito);
		} else {
			super.setGabarito("Questao sem gabarito!");
		}
	}
	
	public void setCodigo(String codigo) {
		super.setCodigo(codigo,  'D');
	}
	
	public String toString() {
		String saida;
		saida = "----Questao Discursiva----";
		saida += super.toString();
		return saida;
	}
}
