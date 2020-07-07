package model.VO;

public class ExataVO extends DisciplinaVO {
	
	public ExataVO() {
		super();
		this.setCodigo(null);
	}
	
	public void setCodigo(String codigo) {
		super.setCodigo(codigo, "EXA");
	}
}
