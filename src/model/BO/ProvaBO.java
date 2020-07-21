package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InsertException;
import model.DAO.DiscursivaDAO;
import model.DAO.MultiplaEscolhaDAO;
import model.DAO.ProvaDAO;
import model.DAO.QuestaoDAO;
import model.DAO.VerdadeiroOuFalsoDAO;
import model.VO.DiscursivaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;
import model.VO.VerdadeiroOuFalsoVO;

public class ProvaBO extends BaseBO<ProvaVO> {
	
	ProvaDAO dao = new ProvaDAO();
	
	public void cadastrar(ProvaVO prova) throws Exception {
		try {
			dao.inserir(prova);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception();
		}
	}
	
	public void remover(ProvaVO prova) {
		ResultSet rs = dao.listarPorId(prova);
		
		try {
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
		ResultSet rs = dao.listarPorId(prova);
		
		try {
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
		ResultSet rs = dao.listar();
		List<ProvaVO> provas = new ArrayList<>();
		
		try {
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
		ResultSet rs = dao.listarPorId(prova);
		
		try {
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
		DiscursivaDAO dDAO = new DiscursivaDAO();
		MultiplaEscolhaDAO meDAO = new MultiplaEscolhaDAO();
		VerdadeiroOuFalsoDAO vfDAO = new VerdadeiroOuFalsoDAO();
		ResultSet rsD = dDAO.listarQuestaoProva();
		ResultSet rsME = meDAO.listarQuestaoProva();
		ResultSet rsVF = vfDAO.listarQuestaoProva();
		List<QuestaoVO> questoes = new ArrayList<>();
		
		try {
			if (rsD.next()) {
				while (rsD.next()) {
					DiscursivaVO dVO = new DiscursivaVO();
					dVO.setCodigo(rsD.getString("codigo"));
					dVO.setNivel(rsD.getInt("nivel"));
					dVO.setEnunciado(rsD.getString("enunciado"));
					dVO.setIdAssunto(rsD.getLong("id_assunto"));
					dVO.setIdDisciplina(prova.getIdDisciplina());
					questoes.add(dVO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (rsME.next()) {
				while (rsD.next()) {
					MultiplaEscolhaVO meVO = new MultiplaEscolhaVO();
					meVO.setCodigo(rsD.getString("codigo"));
					meVO.setNivel(rsD.getInt("nivel"));
					meVO.setEnunciado(rsD.getString("enunciado"));
					meVO.setIdAssunto(rsD.getLong("id_assunto"));
					meVO.setIdDisciplina(prova.getIdDisciplina());
					questoes.add(meVO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (rsVF.next()) {
				while (rsD.next()) {
					VerdadeiroOuFalsoVO vfVO = new VerdadeiroOuFalsoVO();
					vfVO.setCodigo(rsD.getString("codigo"));
					vfVO.setNivel(rsD.getInt("nivel"));
					vfVO.setEnunciado(rsD.getString("enunciado"));
					vfVO.setIdAssunto(rsD.getLong("id_assunto"));
					vfVO.setIdDisciplina(prova.getIdDisciplina());
					questoes.add(vfVO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return questoes;
	}
}
