package model.DAO;

import java.sql.ResultSet;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.QuestaoVO;

public interface AssuntoInterDAO<VO extends AssuntoVO> extends BaseInterDAO<VO> {
	public ResultSet listarPorConteudo(VO vo);
	public ResultSet listarPorDisciplina(VO vo);
	public ResultSet listarPorDisciplina(DisciplinaVO disciplina);
	public ResultSet listarPorDisciplina(QuestaoVO questao);
}
