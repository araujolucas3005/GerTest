package model.VO;

public class HumanaVO extends DisciplinaVO {
	
	public HumanaVO() {
		super();
		this.setCodigo(null);
	}
	
	public void setCodigo(String codigo) {
		super.setCodigo(codigo, "HUM");
	}
}
