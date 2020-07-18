package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DAO.ProvaDAO;
import model.VO.ProvaVO;

public class ProvaBO extends BaseBO<ProvaVO> {
	
	ProvaDAO dao = new ProvaDAO();
	
	public void cadastrar(ProvaVO prova) {
		dao.inserir(prova);
	}
	
	public void remover(ProvaVO prova) {
		ResultSet rs = dao.listarPorId(prova);
		
		if (rs.next()) {
			dao.remover(prova);
		} else {
			throw new InsertException("Nao tem prova com esse id!");
		}
	}
	
	public void editar(ProvaVO prova) {
		ResultSet rs = dao.listarPorId(prova);
		
		if (rs.next()) {
			dao.atualizar(prova);
		} else {
			throw new InsertException("Nao tem prova com esse id!");
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
}
