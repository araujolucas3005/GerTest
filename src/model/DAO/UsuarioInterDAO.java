package model.DAO;

import java.sql.ResultSet;

import model.VO.UsuarioVO;

public interface UsuarioInterDAO<VO extends UsuarioVO> extends BaseInterDAO<VO> {
	ResultSet listarPorLogin(VO vo);
}
