package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import model.DAO.DiscursivaDAO;
import model.DAO.QuestaoInterDAO;
import model.VO.DiscursivaVO;

public class DiscursivaBO extends QuestaoBO<DiscursivaVO> implements BaseInterBO<DiscursivaVO> {
	
	QuestaoInterDAO<DiscursivaVO> dao = new DiscursivaDAO<>();
	
	public void cadastrar(DiscursivaVO vo) throws SQLException {
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
	
	public void remover(DiscursivaVO vo) {
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
