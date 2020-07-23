package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import model.DAO.MultiplaEscolhaDAO;
import model.DAO.QuestaoInterDAO;
import model.VO.MultiplaEscolhaVO;

public class MultiplaEscolhaBO extends ObjetivaBO<MultiplaEscolhaVO> implements BaseInterBO<MultiplaEscolhaVO> {
	
	QuestaoInterDAO<MultiplaEscolhaVO> dao = new MultiplaEscolhaDAO<>();
	
	public void cadastrar(MultiplaEscolhaVO vo) {
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
	
	public void remover(MultiplaEscolhaVO vo) {
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
