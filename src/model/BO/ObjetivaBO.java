package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import model.DAO.QuestaoDAO;
import model.DAO.QuestaoInterDAO;
import model.VO.ObjetivaVO;

public class ObjetivaBO<VO extends ObjetivaVO> extends QuestaoBO<VO> implements QuestaoInterBO<VO> {
	
	static private QuestaoInterDAO<ObjetivaVO> dao = new QuestaoDAO<ObjetivaVO>();
	
	public void cadastrar(VO vo) {
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
