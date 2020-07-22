package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.AutenticationException;
import model.DAO.BaseInterDAO;
import model.DAO.UsuarioDAO;
import model.VO.UsuarioVO;

public class UsuarioBO<VO extends UsuarioVO> extends BaseInterBO<VO> {
	static private BaseInterDAO<UsuarioVO> usuDAO = new UsuarioDAO<UsuarioVO>();
	
	public UsuarioVO autenticar(VO vo) throws AutenticationException {
		ResultSet usuRS = usuDAO.buscarPorLogin(vo);
		try {
			if(usuRS.next()) {
				if (usuRS.getString("senha").equals(vo.getSenha())){
					return vo;
				}
					else throw new AutenticationException();
			}
			else throw new AutenticationException();			
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new AutenticationException();
		}
	}
}