package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import model.DAO.BaseInterDAO;
import model.DAO.MultiplaEscolhaDAO;
import model.VO.MultiplaEscolhaVO;

public class MultiplaEscolhaBO extends ObjetivaBO<MultiplaEscolhaVO> {
	
	BaseInterDAO<MultiplaEscolhaVO> dao = new MultiplaEscolhaDAO();
	
	public void cadastrar(MultiplaEscolhaVO vo) throws Exception {
		ResultSet rs;

		try {
			rs = dao.listarPorCodigo(vo);
			if (rs.next()) {
				throw new InsertException("Ja existe essa questao!");
			} else {
				dao.inserir(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remover(MultiplaEscolhaVO vo) throws Exception {
		ResultSet rs;

		try {
			rs = dao.listarPorCodigo(vo);
			if (rs.next()) {
				throw new InsertException("Ja existe essa questao!");
			} else {
				dao.inserir(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
