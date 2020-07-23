package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InsertException;
import model.DAO.DiscursivaDAO;
import model.DAO.QuestaoInterDAO;
import model.VO.DiscursivaVO;
import model.VO.QuestaoVO;

public class DiscursivaBO<VO extends DiscursivaVO> extends QuestaoBO<VO> implements BaseInterBO<VO> {
	
	QuestaoInterDAO<DiscursivaVO> dao = new DiscursivaDAO<>();
	
	public void cadastrar(VO vo) throws SQLException {
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
	
	@SuppressWarnings("unchecked")
	public List<VO> listarPorDisciplina(VO vo) {
		ResultSet rs = dao.listarPorDisciplina(vo);
		List<VO> assuntos = new ArrayList<>();

		try {
			while (rs.next()) {
				QuestaoVO questao = new DiscursivaVO();
				questao.setIdQuestao(rs.getLong("id_questao"));
				questao.setNivel(rs.getInt("nivel"));
				questao.setEnunciado(rs.getString("enunciado"));
				questao.setGabarito(rs.getString("gabarito"));
				questao.setIdDisciplina(rs.getLong("id_disciplina"));
				questao.setIdAssunto(rs.getLong("id_assunto"));
				questao.setTipo(rs.getString("tipo"));
				assuntos.add((VO) questao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assuntos;
	}
}
