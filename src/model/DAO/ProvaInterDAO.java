package model.DAO;

import java.sql.ResultSet;

import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public interface ProvaInterDAO<VO extends ProvaVO> extends BaseInterDAO<VO> {
	public void inserirQuestaoAvulsa(VO vo, QuestaoVO questao);
	public ResultSet listarPorDisciplina(VO vo);
	public ResultSet listarQuestoes(VO vo);
}
