package model.VO;

public class BiologicaVO extends DisciplinaVO {
	
	public BiologicaVO() {
		super();
		this.setCodigo(null);
	}
	
	public void setCodigo(String codigo) {
		super.setCodigo(codigo, "BIO");
	}
}
