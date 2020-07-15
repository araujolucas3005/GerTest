package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.DisciplinaVO;

public class DisciplinaDAO extends BaseDAO<DisciplinaVO> {

	public void inserir(DisciplinaVO disciplina) {

		conn = getConnection();
		String sql = "insert into Disciplina (nome,codigo) values (?,?)";
		// String sqlAssunto = "insert into Assuntos (assunto, id_disciplina) values
		// (?,?)";
		PreparedStatement ptst;

		try {
			ptst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setNString(1, disciplina.getNome());
			ptst.setNString(2, disciplina.getCodigo());
			
			int affectedRows = ptst.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}

			/*
			 * ResultSet rs = ptst.getGeneratedKeys(); long id = 0; while (rs.next()) { id =
			 * rs.getLong(1); }
			 */

			/*
			 * for (String assunto : disciplina.getAssuntos()) { ptst =
			 * conn.prepareStatement(sqlAssunto); ptst.setNString(1, assunto);
			 * ptst.setLong(2, id); ptst.execute(); }
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover(DisciplinaVO disciplina) {
		conn = getConnection();
		String sql = "remove from Disciplina where codigo = ?";
		PreparedStatement ptst = null;

		try {
			ptst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setNString(1, disciplina.getCodigo());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Long id = null;
		ResultSet rs;
		try {
			rs = ptst.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// remover tambem os assuntos
		sql = "remove * from Assuntos where id_disciplina = ?";
		try {
			ptst = conn.prepareStatement(sql);
			ptst.setLong(1, id);
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// remover tambem as questoes
		sql = "remove * from Questao where id_disciplina = ?";
		try {
			ptst = conn.prepareStatement(sql);
			ptst.setLong(1, id);
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizar (DisciplinaVO disciplina) {
		
		conn = getConnection();
		String sql = "update Disciplina set nome = ?, codigo = ?";
		PreparedStatement ptst;
		
		try {
			ptst = conn.prepareStatement(sql);
			ptst.setNString(1, disciplina.getNome());
			ptst.setNString(2, disciplina.getCodigo());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet listarPorId(DisciplinaVO disciplina) {
		String sql = "select * from Disciplina where id = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, disciplina.getId());
			rs = ptst.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet listarPorNome(DisciplinaVO disciplina) {
		String sql = "select * from Disciplina where nome = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, disciplina.getNome());
			rs = ptst.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet listarPorCodigo(DisciplinaVO disciplina) {
		String sql = "select * from Disciplina where codigo = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, disciplina.getNome());
			rs = ptst.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet listar() {
		conn = getConnection();
		String sql = "select * from Disciplina";
		Statement st;
		ResultSet rs;
		rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
