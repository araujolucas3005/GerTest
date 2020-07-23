package model.BO;

import java.util.List;

import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public interface ProvaInterBO<VO extends ProvaVO> extends BaseInterBO<VO>{
	public void cadastrarQuestaoAvulsa(VO vo, QuestaoVO questao);
	public List<VO> listarPorDisciplina(VO vo);
	public List<QuestaoVO> listarQuestoes(VO vo);
}
