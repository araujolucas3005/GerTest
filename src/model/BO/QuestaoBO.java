package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InsertException;
import model.DAO.DiscursivaDAO;
import model.DAO.MultiplaEscolhaDAO;
import model.DAO.QuestaoDAO;
import model.DAO.VerdadeiroOuFalsoDAO;
import model.VO.DiscursivaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.QuestaoVO;
import model.VO.VerdadeiroOuFalsoVO;

public class QuestaoBO<VO extends QuestaoVO> extends BaseBO<VO> implements QuestaoInterBO<VO> {

	static private QuestaoDAO<QuestaoVO> questaoDAO = new QuestaoDAO<QuestaoVO>();
	static private QuestaoDAO<DiscursivaVO> discDAO = new DiscursivaDAO();
	static private QuestaoDAO<MultiplaEscolhaVO> meDAO = new MultiplaEscolhaDAO();
	static private QuestaoDAO<VerdadeiroOuFalsoVO> vfDAO = new VerdadeiroOuFalsoDAO();

	public List<QuestaoVO> listarTodos() {
		List<QuestaoVO> questoes = new ArrayList<>();

		ResultSet discRs = discDAO.listar();
		try {
			while (discRs.next()) {
				DiscursivaVO discursiva = new DiscursivaVO();
				discursiva.setCodigo(discRs.getString("codigo"));
				discursiva.setIdAssunto(discRs.getLong("id_assunto"));
				discursiva.setEnunciado(discRs.getString("enunciado"));
				discursiva.setGabarito(discRs.getString("gabarito"));
				discursiva.setIdQuestao(discRs.getLong("id_questao"));
				discursiva.setNivel(discRs.getInt("nivel"));
				discursiva.setIdDisciplina(discRs.getLong("id_disciplina"));
				discursiva.setTipo(discRs.getString("tipo"));
				questoes.add(discursiva);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet meRs = meDAO.listar();
		try {
			while (meRs.next()) {
				MultiplaEscolhaVO multiplaEscolha = new MultiplaEscolhaVO();
				multiplaEscolha.setCodigo(meRs.getString("codigo"));
				multiplaEscolha.setIdAssunto(meRs.getLong("id_assunto"));
				multiplaEscolha.setEnunciado(meRs.getString("enunciado"));
				multiplaEscolha.setGabarito(meRs.getString("gabarito"));
				multiplaEscolha.setIdQuestao(meRs.getLong("id_questao"));
				multiplaEscolha.setNivel(meRs.getInt("nivel"));
				multiplaEscolha.setIdDisciplina(meRs.getLong("id_disciplina"));
				multiplaEscolha.setTipo(meRs.getString("tipo"));
				questoes.add(multiplaEscolha);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet vfRs = vfDAO.listar();
		try {
			while (vfRs.next()) {
				VerdadeiroOuFalsoVO vf = new VerdadeiroOuFalsoVO();
				vf.setCodigo(vfRs.getString("codigo"));
				vf.setIdAssunto(vfRs.getLong("id_assunto"));
				vf.setEnunciado(vfRs.getString("enunciado"));
				vf.setGabarito(vfRs.getString("gabarito"));
				vf.setIdQuestao(vfRs.getLong("id_questao"));
				vf.setNivel(vfRs.getInt("nivel"));
				vf.setIdDisciplina(vfRs.getLong("id_disciplina"));
				vf.setTipo(vfRs.getString("tipo"));
				questoes.add(vf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questoes;
	}

	public void cadastrar(VO questao) throws InsertException, SQLException {
		ResultSet rs;

		try {
			rs = questaoDAO.listarPorCodigo(questao);
			if (rs.next()) {
				throw new InsertException("Ja existe essa questao!");
			} else {
				questaoDAO.inserir(questao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(VO questao) {
		try {
			ResultSet rs = questaoDAO.listarPorCodigo(questao);
			if (rs.next()) {
				throw new InsertException("Ja existe questao com esse codigo!");
			} else {
				questaoDAO.atualizar(questao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover(VO questao) {
		try {
			ResultSet rs = questaoDAO.listarPorId(questao);
			if (rs.next()) {
				questaoDAO.remover(questao);
			}
			else {
				throw new InsertException("Essa questao nao existe!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
	}

	public VO buscarPorId(VO questao) {
		ResultSet rs;
		try {
			rs = questaoDAO.listarPorId(questao);
			if (rs.next()) {
				questao.setIdAssunto(rs.getLong("id_assunto"));
				questao.setCodigo(rs.getString("codigo"));
				questao.setEnunciado(rs.getString("enunciado"));
				questao.setGabarito(rs.getString("gabarito"));
				questao.setIdDisciplina(rs.getLong("id_disciplina"));
				questao.setIdQuestao(rs.getLong("id_questao"));
				questao.setNivel(rs.getInt("nivel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questao;
	}
}