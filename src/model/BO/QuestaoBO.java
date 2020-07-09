package model.BO;

import java.util.ArrayList;
import java.util.List;
import model.VO.DisciplinaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.QuestaoVO;
import model.VO.VerdadeiroOuFalsoVO;

public class QuestaoBO {

	// simulando o banco de dados
	List<QuestaoVO> bancoDeDados = new ArrayList<>();

	public QuestaoVO[] listar() {
		QuestaoVO q1 = new MultiplaEscolhaVO();
		QuestaoVO q2 = new VerdadeiroOuFalsoVO();

		QuestaoVO[] questoes = new QuestaoVO[2];
		questoes[0] = q1;
		questoes[1] = q2;

		return questoes;
	}

	public void cadastrar(QuestaoVO questao) {
		if (questao != null) {
			bancoDeDados.add(questao);
		}
	}

	public void adicionar(QuestaoVO questao, DisciplinaVO disciplina) {
		if (questao != null && disciplina != null && questao.getDiscip() != null) {
			questao.setDiscip(disciplina);
		}
	}

	public void adicionar(QuestaoVO questao, String codigoEnunciadoAssuntoOuGabarito, String tipo) {
		if (questao != null && codigoEnunciadoAssuntoOuGabarito != null && tipo != null) {
			switch (tipo) {
			case "codigo":
				if (questao.getCodigo() == null)
					questao.setCodigo(codigoEnunciadoAssuntoOuGabarito);
			case "enunciado":
				if (questao.getEnunciado() == null)
					questao.setEnunciado(codigoEnunciadoAssuntoOuGabarito);
			case "assunto":
				if (questao.getAssunto() == null)
					questao.setAssunto(codigoEnunciadoAssuntoOuGabarito);
			case "gabarito":
				if (questao.getGabarito() == null)
					questao.setGabarito(codigoEnunciadoAssuntoOuGabarito);
			}
		}
	}
	
	public void adicionar(QuestaoVO questao, String opcao) {
		if (questao != null && opcao != null) {
			if (questao instanceof VerdadeiroOuFalsoVO) {
				VerdadeiroOuFalsoVO vf = (VerdadeiroOuFalsoVO) questao;
				vf.addOpcao(opcao);
				questao = vf;
			} else {
				MultiplaEscolhaVO me = (MultiplaEscolhaVO) questao;
				me.addOpcao(opcao);
				questao = me;
			}
		}
	}

	public void editar(QuestaoVO questao, DisciplinaVO disciplina) {
		if (questao != null && disciplina != null) {
			questao.setDiscip(disciplina);
		}
	}

	public void editar(QuestaoVO questao, String codigoEnunciadoAssuntoOuGabarito, String tipo) {
		if (questao != null && codigoEnunciadoAssuntoOuGabarito != null && tipo != null) {
			switch (tipo) {
			case "codigo":
				questao.setCodigo(codigoEnunciadoAssuntoOuGabarito);
			case "enunciado":
				questao.setEnunciado(codigoEnunciadoAssuntoOuGabarito);
			case "assunto":
				questao.setAssunto(codigoEnunciadoAssuntoOuGabarito);
			case "gabarito":
				questao.setGabarito(codigoEnunciadoAssuntoOuGabarito);
			}
		}
	}

	public void editar(QuestaoVO questao, String opcao, int numeroDaOpcao) {
		if (questao != null && opcao != null && numeroDaOpcao != 0) {
			numeroDaOpcao--;
			char letraOpcao = 'a';
			letraOpcao +=  numeroDaOpcao;
			if (questao instanceof VerdadeiroOuFalsoVO) {
				VerdadeiroOuFalsoVO vf = (VerdadeiroOuFalsoVO) questao;
				vf.getOpcoes().set(numeroDaOpcao, String.valueOf(letraOpcao) + ") " + opcao);
				questao = vf;
			} else {
				MultiplaEscolhaVO me = (MultiplaEscolhaVO) questao;
				me.getOpcoes().set(numeroDaOpcao, String.valueOf(letraOpcao) + ") " + opcao);
				questao = me;
			}
		}
	}

	public void remover(QuestaoVO questao) {
		if (questao != null) {
			bancoDeDados.remove(questao);
		}
	}
	
	public void remover(QuestaoVO questao, int numeroOpcao) {
		if (questao != null && numeroOpcao != 0) {
			numeroOpcao--;
			if (questao instanceof VerdadeiroOuFalsoVO) {
				VerdadeiroOuFalsoVO vf = (VerdadeiroOuFalsoVO) questao;
				vf.removeOpcao(numeroOpcao);
				questao = vf;
			} else {
				MultiplaEscolhaVO me = (MultiplaEscolhaVO) questao;
				me.removeOpcao(numeroOpcao);
				questao = me;
			}
		}
	}

	public QuestaoVO buscar(QuestaoVO questao) {
		if (questao != null) {
			for (QuestaoVO q : bancoDeDados) {
				if (questao == q) {
					return questao;
				}
			}
		}
		return null;
	}
}
