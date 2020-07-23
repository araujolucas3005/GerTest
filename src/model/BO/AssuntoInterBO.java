package model.BO;

import java.sql.SQLException;
import java.util.List;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.QuestaoVO;

public interface AssuntoInterBO<VO extends AssuntoVO> extends BaseInterBO<VO> {
	public List<VO> listarPorDisciplina(VO vo) throws SQLException;
	public List<VO> listarPorDisciplina(DisciplinaVO vo) throws SQLException;
	public List<VO> listarPorDisciplina(QuestaoVO vo) throws SQLException;
}
