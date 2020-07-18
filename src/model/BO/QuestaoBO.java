package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List<QuestaoVO> listarTodos() throws InsertException {
		List<QuestaoVO> questoes = new ArrayList<>();

		try {
			ResultSet discRs = discDAO.listar();
			if (!discRs.next()) {
				throw new Exception("Sem questoes Discursivas!");
			} else {
				while (discRs.next()) {
					DiscursivaVO discursiva = new DiscursivaVO();
					discursiva.setCodigo(discRs.getString("codigo"));
					discursiva.setIdAssunto(discRs.getLong("id_assunto"));
					discursiva.setEnunciado(discRs.getString("enunciado"));
					discursiva.setGabarito(discRs.getString("gabarito"));
					discursiva.setIdQuestao(discRs.getLong("id_questao"));
					discursiva.setNivel(discRs.getInt("nivel"));
					discursiva.setIdDisciplina(discRs.getLong("id_disciplina"));
					questoes.add(discursiva);
				}
			}

			ResultSet meRs = meDAO.listar();
			if (!discRs.next()) {
				throw new Exception("Sem questoes Discursivas!");
			} else {
				while (discRs.next()) {
					MultiplaEscolhaVO multiplaEscolha = new MultiplaEscolhaVO();
					multiplaEscolha.setCodigo(meRs.getString("codigo"));
					multiplaEscolha.setIdAssunto(meRs.getLong("id_assunto"));
					multiplaEscolha.setEnunciado(meRs.getString("enunciado"));
					multiplaEscolha.setGabarito(meRs.getString("gabarito"));
					multiplaEscolha.setIdQuestao(meRs.getLong("id_questao"));
					multiplaEscolha.setNivel(meRs.getInt("nivel"));
					questoes.add(multiplaEscolha);
				}
			}

			ResultSet vfRs = vfDAO.listar();
			if (!discRs.next()) {
				throw new Exception("Sem questoes Discursivas!");
			} else {
				while (discRs.next()) {
					VerdadeiroOuFalsoVO vf = new VerdadeiroOuFalsoVO();
					vf.setCodigo(vfRs.getString("codigo"));
					vf.setIdAssunto(vfRs.getLong("id_assunto"));
					vf.setEnunciado(vfRs.getString("enunciado"));
					vf.setGabarito(vfRs.getString("gabarito"));
					vf.setIdQuestao(vfRs.getLong("id_questao"));
					vf.setNivel(vfRs.getInt("nivel"));
					questoes.add(vf);
				}
			}
		} catch (Exception e) {
			throw new Exception("A insercao falhou. Nenhum id foi retornado.");
		}
		return questoes;
	}
	
	public void cadastrar(VO questao) throws InsertException {
		ResultSet rs;

		try {
			rs = questaoDAO.listarPorCodigo(questao);
			if (rs.next()) {
				throw new Exception("Ja existe essa questao!");
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
		ResultSet rs;
		try {
			rs = questaoDAO.listarPorCodigo(questao);
			if (!rs.next()) {
				throw new InsertException("Essa questao nao existe!");
			} else {
				while (rs.next()) {
					questaoDAO.remover(questao);
				}
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
