package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DAO.ObjetivaDAO;
import model.VO.ObjetivaVO;

public class ObjetivaBO<VO extends ObjetivaVO> extends QuestaoBO<VO> {
	
	static private ObjetivaDAO<ObjetivaVO> dao = new ObjetivaDAO<ObjetivaVO>();
	
	public void cadastrar(VO vo) {
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
	
	public void remover(VO vo) {
		ResultSet rs;
		try {
			rs = dao.listarPorCodigo(vo);
			if (!rs.next()) {
				throw new InsertException("Essa questao nao existe!");
			} else {
				while (rs.next()) {
					dao.remover(vo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
