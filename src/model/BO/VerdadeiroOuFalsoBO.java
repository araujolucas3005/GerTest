package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import model.DAO.BaseInterDAO;
import model.DAO.VerdadeiroOuFalsoDAO;
import model.VO.VerdadeiroOuFalsoVO;

public class VerdadeiroOuFalsoBO extends ObjetivaBO<VerdadeiroOuFalsoVO> {

	BaseInterDAO<VerdadeiroOuFalsoVO> dao = new VerdadeiroOuFalsoDAO();
	
	public void cadastrar(VerdadeiroOuFalsoVO vo) throws Exception {
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
	
	public void remover(VerdadeiroOuFalsoVO vo) throws Exception {
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
