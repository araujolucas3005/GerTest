package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.VO.QuestaoVO;

public interface QuestaoInterDAO<VO extends QuestaoVO> extends BaseInterDAO<VO> {
	public ResultSet listarPorIdPessoa(VO vo) throws SQLException;
}
