package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsertException;
import model.DAO.DiscursivaDAO;
import model.VO.DiscursivaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.QuestaoVO;

public class DiscursivaBO extends QuestaoBO<DiscursivaVO> {
	
	DiscursivaDAO dao = new DiscursivaDAO();
	
	public void cadastrar(DiscursivaVO vo) {
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
	
	public void remover(DiscursivaVO vo) {
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
