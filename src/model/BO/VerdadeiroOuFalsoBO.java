package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import model.DAO.QuestaoInterDAO;
import model.DAO.VerdadeiroOuFalsoDAO;
import model.VO.VerdadeiroOuFalsoVO;

public class VerdadeiroOuFalsoBO extends ObjetivaBO<VerdadeiroOuFalsoVO> implements BaseInterBO<VerdadeiroOuFalsoVO> {

	private QuestaoInterDAO<VerdadeiroOuFalsoVO> dao = new VerdadeiroOuFalsoDAO();

	public void cadastrar(VerdadeiroOuFalsoVO vo) {
		ResultSet rs;

		try {
			rs = dao.listarPorCodigo(vo);
			if (rs.next()) {
				throw new InsertException("Ja existe essa questao!");
			} else {
				try {
					dao.inserir(vo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				try {
					dao.inserir(vo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
