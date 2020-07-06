package model.VO;

public class BiologicaVO extends DisciplinaVO {
	
	public void setCodigo(String codigo) {
		
		if (codigo != null && !codigo.isEmpty()) {
			if (super.testeCodigoFormatoCorreto(codigo, "BIO")) {
				super.setCodigo(codigo);
			} else {
				super.gerarCodigoAleatorio("BIO");
			}
		} else {
			super.setCodigo(gerarCodigoAleatorio("BIO"));
		}
	}
}
