package model.VO;

public class ExataVO extends DisciplinaVO {
	
	public void setCodigo(String codigo) {
		
		if (codigo != null && !codigo.isEmpty()) {
			if (super.testeCodigoFormatoCorreto(codigo, "EXA")) {
				super.setCodigo(codigo);
			} else {
				super.gerarCodigoAleatorio("EXA");
			}
		} else {
			super.setCodigo(gerarCodigoAleatorio("EXA"));
		}
	}
}
