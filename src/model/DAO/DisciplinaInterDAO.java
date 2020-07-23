package model.DAO;

import java.sql.ResultSet;

import model.VO.DisciplinaVO;

public interface DisciplinaInterDAO<VO extends DisciplinaVO> extends BaseInterDAO<VO> {
	public ResultSet listarPorCodigo(VO vo);
	public ResultSet listarPorNome(VO vo);
}
