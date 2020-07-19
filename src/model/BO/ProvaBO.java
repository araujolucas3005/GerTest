package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InsertException;
import model.DAO.ProvaDAO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public class ProvaBO extends BaseBO<ProvaVO> {
	
	ProvaDAO dao = new ProvaDAO();
	
	public void cadastrar(ProvaVO prova) {
		dao.inserir(prova);
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
		ResultSet rs = dao.listarQuestoes(prova);
		DiscursivaDAO dDAO = new DiscursivaDAO();
		MultiplaEscolhaDAO meDAO = new MultiplaEscolhaDAO();
		VerdadeiroOuFalsoDAO vfDAO = new VerdadeiroOuFalsoDAO();
	}
}
