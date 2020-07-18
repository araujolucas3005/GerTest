package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DAO.QuestaoDAO;
import model.DAO.VerdadeiroOuFalsoDAO;
import model.VO.MultiplaEscolhaVO;
import model.VO.QuestaoVO;
import model.VO.VerdadeiroOuFalsoVO;

public class VerdadeiroOuFalsoBO extends ObjetivaBO<VerdadeiroOuFalsoVO> {

	VerdadeiroOuFalsoDAO dao = new VerdadeiroOuFalsoDAO();
	
	public void cadastrar(VerdadeiroOuFalsoVO vo) {
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
	
	public void remover(VerdadeiroOuFalsoVO vo) {
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
