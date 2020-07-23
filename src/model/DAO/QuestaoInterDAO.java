package model.DAO;

import java.sql.ResultSet;

import model.VO.QuestaoVO;

public interface QuestaoInterDAO<VO extends QuestaoVO> extends BaseInterDAO<VO> {
	public ResultSet listarPorCodigo(VO vo);
	public ResultSet listarPorDisciplina(VO vo);
	public void removerDaProva(VO vo);
}