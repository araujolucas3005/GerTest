package model.BO;
import java.util.ArrayList;
import java.util.List;

import model.VO.DisciplinaVO;

public class DisciplinaBO {
	
	public void cadastrar(DisciplinaVO disciplina) {
		 List<DisciplinaVO> exemplo = new ArrayList<DisciplinaVO>();
		 exemplo.add(disciplina);
	}
		
	public void editar(DisciplinaVO disciplina) {
		disciplina.setNome("banco de dados");
		disciplina.setCodigo("EXA101");
	}
	
	public void remover(DisciplinaVO disciplina) {
		List<DisciplinaVO> exemplo = new ArrayList<DisciplinaVO>();
		exemplo.remove(disciplina);			
	}
	
	 /*public void listar(lista de disciplinaVO) {
		disciplina(na posicao da lista).toString();
		
	} */
}