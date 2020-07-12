package model.BO;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import model.VO.DisciplinaVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;

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

	public ProvaVO gerarProva(DisciplinaVO disciplina) {
		ProvaVO prova = new ProvaVO();
		Random gerador = new Random();
		List<QuestaoVO> questoesNivel1 = new ArrayList<>();
		List<QuestaoVO> questoesNivel2 = new ArrayList<>();
		List<QuestaoVO> questoesNivel3 = new ArrayList<>();
		List<QuestaoVO> questoesNivel4 = new ArrayList<>();

		for (QuestaoVO questao : disciplina.getQuestoes()) {
			switch (questao.getNivel()) {
			case 1:
				questoesNivel1.add(questao);
			case 2:
				questoesNivel2.add(questao);
			case 3:
				questoesNivel3.add(questao);
			case 4:
				questoesNivel4.add(questao);
			}
		}
		
		List<Integer> posicoesQuestoesNivel1 = new ArrayList<>();
		List<Integer> posicoesQuestoesNivel2 = new ArrayList<>();
		List<Integer> posicoesQuestoesNivel3 = new ArrayList<>();
		List<Integer> posicoesQuestoesNivel4 = new ArrayList<>();
		
		int i = 0;
		while (i < prova.getNivel1()) {
			int numQuestao = gerador.nextInt(questoesNivel1.size());
			// para nao gerar questoes repetidas
			if (!posicoesQuestoesNivel1.contains(numQuestao)) {
				posicoesQuestoesNivel1.add(numQuestao);
				prova.addQuestao(questoesNivel1.get(numQuestao));
				i++;
			}
		}
		
		i = 0;
		while (i < prova.getNivel2()) {
			int numQuestao = gerador.nextInt(questoesNivel2.size());
			if (!posicoesQuestoesNivel2.contains(numQuestao)) {
				posicoesQuestoesNivel2.add(numQuestao);
				prova.addQuestao(questoesNivel2.get(numQuestao));
				i++;
			}
		}
		
		i = 0;
		while (i < prova.getNivel3()) {
			int numQuestao = gerador.nextInt(questoesNivel3.size());
			if (!posicoesQuestoesNivel3.contains(numQuestao)) {
				posicoesQuestoesNivel3.add(numQuestao);
				prova.addQuestao(questoesNivel3.get(numQuestao));
				i++;
			}
		}
		
		i = 0;
		while (i < prova.getNivel4()) {
			int numQuestao = gerador.nextInt(questoesNivel4.size());
			if (!posicoesQuestoesNivel4.contains(numQuestao)) {
				posicoesQuestoesNivel4.add(numQuestao);
				prova.addQuestao(questoesNivel4.get(numQuestao));
				i++;
			}
		}
		return prova;
	}
}

/*
 * public void listar(lista de disciplinaVO) { disciplina(na posicao da
 * lista).toString();
 * 
 * }
 */