package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InsertException;
import model.DAO.ProvaDAO;
import model.DAO.ProvaInterDAO;
import model.VO.DiscursivaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;
import model.VO.VerdadeiroOuFalsoVO;

public class ProvaBO extends BaseBO<ProvaVO> implements ProvaInterBO<ProvaVO> {

	ProvaInterDAO<ProvaVO> dao = new ProvaDAO<>();

	public void cadastrar(ProvaVO vo) throws Exception {
		try {
			dao.inserir(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception();
		}
	}

	public void cadastrarQuestaoAvulsa(ProvaVO prova, QuestaoVO questao) {
		dao.inserirQuestaoAvulsa(prova, questao);
	}

	public void remover(ProvaVO prova) {
		try {
			ResultSet rs = dao.listarPorId(prova);
			if (rs.next()) {
				dao.remover(prova);
			} else {
				throw new InsertException("Nao tem prova com esse id!");
			}
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar(ProvaVO prova) {
		try {
			ResultSet rs = dao.listarPorId(prova);
			if (rs.next()) {
				dao.atualizar(prova);
			} else {
				throw new InsertException("Nao tem prova com esse id!");
			}
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<ProvaVO> listar(ProvaVO prova) {
		List<ProvaVO> provas = new ArrayList<>();

		try {
			ResultSet rs = dao.listar();
			while (rs.next()) {
				ProvaVO prov = new ProvaVO();
				prov.setId(rs.getLong("id"));
				prov.setNivel1(rs.getInt("nivel1"));
				prov.setNivel2(rs.getInt("nivel2"));
				prov.setNivel3(rs.getInt("nivel3"));
				prov.setNivel4(rs.getInt("nivel4"));
				provas.add(prov);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return provas;
	}

	public List<ProvaVO> listarPorDisciplina(ProvaVO prova) {
		ResultSet rs = dao.listarPorDisciplina(prova);
		List<ProvaVO> provas = new ArrayList<>();

		try {
			while (rs.next()) {
				ProvaVO prov = new ProvaVO();
				prov.setId(rs.getLong("id"));
				prov.setIdDisciplina(rs.getLong("id_disciplina"));
				prov.setNivel1(rs.getInt("nivel1"));
				prov.setNivel2(rs.getInt("nivel2"));
				prov.setNivel3(rs.getInt("nivel3"));
				prov.setNivel4(rs.getInt("nivel4"));
				provas.add(prov);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return provas;
	}

	public ProvaVO buscarPorId(ProvaVO prova) {
		try {
			ResultSet rs = dao.listarPorId(prova);
			while (rs.next()) {
				prova.setNivel1(rs.getInt("nivel1"));
				prova.setNivel2(rs.getInt("nivel2"));
				prova.setNivel3(rs.getInt("nivel3"));
				prova.setNivel4(rs.getInt("nivel4"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prova;
	}

	public List<QuestaoVO> listarQuestoes(ProvaVO prova) {
		List<QuestaoVO> questoes = new ArrayList<>();
		ResultSet rs = dao.listarQuestoes(prova);
		try {
			while (rs.next()) {
				if (rs.getLong("id_disciplina") == prova.getIdDisciplina()) {
					if (rs.getString("tipo").equals("Discursiva")) {
						DiscursivaVO dVO = new DiscursivaVO();
						dVO.setCodigo(rs.getString("codigo"));
						dVO.setNivel(rs.getInt("nivel"));
						dVO.setGabarito(rs.getString("gabarito"));
						dVO.setEnunciado(rs.getString("enunciado"));
						dVO.setIdQuestao(rs.getLong("id_questao"));
						dVO.setTipo(rs.getString("tipo"));
						dVO.setIdDisciplina(rs.getLong("id_disciplina"));
						questoes.add(dVO);
					} else if (rs.getString("tipo").equals("Multipla Escolha")) {
						MultiplaEscolhaVO mVO = new MultiplaEscolhaVO();
						mVO.setCodigo(rs.getString("codigo"));
						mVO.setNivel(rs.getInt("nivel"));
						mVO.setGabarito(rs.getString("gabarito"));
						mVO.setEnunciado(rs.getString("enunciado"));
						mVO.setIdQuestao(rs.getLong("id_questao"));
						mVO.setTipo(rs.getString("tipo"));
						questoes.add(mVO);
					} else {
						VerdadeiroOuFalsoVO vfVO = new VerdadeiroOuFalsoVO();
						vfVO.setCodigo(rs.getString("codigo"));
						vfVO.setNivel(rs.getInt("nivel"));
						vfVO.setGabarito(rs.getString("gabarito"));
						vfVO.setEnunciado(rs.getString("enunciado"));
						vfVO.setIdQuestao(rs.getLong("id_questao"));
						vfVO.setTipo(rs.getString("tipo"));
						questoes.add(vfVO);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questoes;
	}
}