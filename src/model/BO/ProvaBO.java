package model.BO;

import java.util.ArrayList;
import java.util.List;

import model.VO.ProvaVO;

public class ProvaBO {
	
	public void editar(ProvaVO prova) {
		
	}

	public void excluir(ProvaVO prova) {
		List<ProvaVO> exemplo = new ArrayList<ProvaVO>();
		exemplo.remove(prova);
		
	}
	
	public void buscar(ProvaVO prova) {
		List<ProvaVO> exemplo = new ArrayList<ProvaVO>();
		int tamanhoLista = exemplo.size();
		for (int i = 0; i < tamanhoLista; i++)
			if (exemplo.contains(prova))
				prova.toString();	
	}
	
	/*public void gerarRelatorio()
	{
		Escreve todas as provas registradas no BD.
	}*/
	
	/*public void gerarProva() {
		Seleciona questoes de uma determinada disciplina para gerar uma prova especifica, aleatoriamente.
	}*/
	
	public void cadastrar(ProvaVO prova) {
		List<ProvaVO> exemplo = new ArrayList<ProvaVO>();
		exemplo.add(prova);
	}
}
