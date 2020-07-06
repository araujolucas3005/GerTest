package model.VO;

public class HumanaVO extends DisciplinaVO {
	
	public void setCodigo(String codigo) {
		
		if (codigo != null && !codigo.isEmpty()) {
			if (super.testeCodigoFormatoCorreto(codigo, "HUM")) {
				super.setCodigo(codigo);
			} else {
				super.gerarCodigoAleatorio("HUM");
			}
		} else {
			super.setCodigo(gerarCodigoAleatorio("HUM"));
		}
	}
}
