package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.ProvaVO;

public class ProvaDAO extends BaseDAO<ProvaVO> {

	public void inserir(ProvaVO prova) {
		String sql = "insert into Prova (id_disciplina,qtdQuestoesNivel1,qtdQuestoesNivel2,qtdQuestoesNivel3,qtdQuestoesNivel4) values (?,?,?,?,?)";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, prova.getIdDisciplina());
			ptst.setInt(2, prova.getNivel1());
			ptst.setInt(3, prova.getNivel2());
			ptst.setInt(4, prova.getNivel3());
			ptst.setInt(5, prova.getNivel4());

			int affectedRows = ptst.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "select * from Questao where id_disciplina = ? AND nivel = ? order by RAND() LIMIT ?";
		String sql2 = "insert into Prova_Questao (id_questao,id_prova) values (?,?)";
		
		PreparedStatement ptst2;
		ResultSet rs;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst2 = getConnection().prepareStatement(sql2);
			ptst.setLong(1, prova.getIdDisciplina());
			ptst.setInt(2, 1);
			ptst.setInt(3, prova.getNivel1());
			rs = ptst.executeQuery();
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				ptst2.executeUpdate();
			}
			
			ptst.setInt(2, 2);
			ptst.setInt(3, prova.getNivel2());
			rs = ptst.executeQuery();
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				ptst2.executeUpdate();
			}
			
			ptst.setInt(2, 3);
			ptst.setInt(3, prova.getNivel3());
			rs = ptst.executeQuery();
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				ptst2.executeUpdate();
			}
			
			ptst.setInt(2, 4);
			ptst.setInt(3, prova.getNivel4());
			rs = ptst.executeQuery();
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				ptst2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void remover(ProvaVO prova) {
		String sql = "delete from Prova_Questao where id_prova = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, prova.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "delete from Prova where id = ?";

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, prova.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizar(ProvaVO prova) {
		String sql = "update Prova_Questao set id_questao = ? where id_prova = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, prova.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet listar() {
		String sql = "select * from Prova";
		Statement st;
		ResultSet rs = null;

		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet listarPorDisciplina(ProvaVO prova) {
		String sql = "select * from Prova where id_disciplina = ?";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, prova.getIdDisciplina());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet listarPorId(ProvaVO prova) {
		String sql = "select * from Prova where id = ?";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, prova.getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
