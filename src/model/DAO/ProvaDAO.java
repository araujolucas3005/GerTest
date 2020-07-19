package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.ProvaVO;

public class ProvaDAO extends BaseDAO<ProvaVO> {

	public void inserir(ProvaVO prova) {
		String sql = "insert into prova (nivel1,nivel2,nivel3,nivel4, id_disciplina) values (?,?,?,?,?)";
		PreparedStatement ptst = null;

		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setInt(1, prova.getNivel1());
			ptst.setInt(2, prova.getNivel2());
			ptst.setInt(3, prova.getNivel3());
			ptst.setInt(4, prova.getNivel4());
			ptst.setLong(5, prova.getIdDisciplina());

			int affectedRows = ptst.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Long id = new Long(0);
		ResultSet idGerado;
		try {
			idGerado = ptst.getGeneratedKeys();
			idGerado.next();
			id = idGerado.getLong(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prova.setId(id);

		// Inserindo as questoes aleatorias da disciplina na prova
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
			int rowsAffected = 0;
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				rowsAffected++;
			}
			if (rowsAffected < prova.getNivel1()) {
				throw new SQLException("Passou do limite de questoes nivel 1!");
			} else {
				ptst2.executeUpdate();
			}
			
			rowsAffected = 0;
			ptst.setInt(2, 2);
			ptst.setInt(3, prova.getNivel2());
			rs = ptst.executeQuery();
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				rowsAffected++;
			}
			if (rowsAffected < prova.getNivel2()) {
				throw new SQLException("Passou do limite de questoes nivel 2!");
			} else {
				ptst2.executeUpdate();
			}
			
			rowsAffected = 0;
			ptst.setInt(2, 3);
			ptst.setInt(3, prova.getNivel3());
			rs = ptst.executeQuery();
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				rowsAffected++;
			}
			if (rowsAffected < prova.getNivel3()) {
				throw new SQLException("Passou do limite de questoes nivel 3!");
			} else {
				ptst2.executeUpdate();
			}
			
			rowsAffected = 0;
			ptst.setInt(2, 4);
			ptst.setInt(3, prova.getNivel4());
			rs = ptst.executeQuery();
			while (rs.next()) {
				ptst2.setLong(1, rs.getLong("id"));
				ptst2.setLong(2, prova.getId());
				rowsAffected++;
			}
			if (rowsAffected < prova.getNivel4()) {
				throw new SQLException("Passou do limite de questoes nivel 4!");
			} else {
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
		String sql = "update prova set nivel1 = ?, nivel2 = ?, nivel3 = ?, nivel4 = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, prova.getNivel1());
			ptst.setLong(1, prova.getNivel2());
			ptst.setLong(1, prova.getNivel3());
			ptst.setLong(1, prova.getNivel4());
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

	public ResultSet listarQuestoes(ProvaVO prova) {
		String sql = "select * from Questao inner join Prova_Questao on Questao.id = Prova_Questao.id_questao where Prova_Questao.id = ?";
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
