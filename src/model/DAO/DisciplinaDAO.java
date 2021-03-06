package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.NomeMuitoLongException;
import model.VO.DisciplinaVO;

public class DisciplinaDAO<VO extends DisciplinaVO> extends BaseDAO<VO> implements DisciplinaInterDAO<VO> {

	public void inserir(DisciplinaVO disciplina) throws NomeMuitoLongException {

		String sql = "insert into Disciplina (nome,codigo) values (?,?)";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, disciplina.getNome());
			ptst.setNString(2, disciplina.getCodigo());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new NomeMuitoLongException();
		}
	}

	public void remover(DisciplinaVO disciplina) {
		PreparedStatement ptst;

		// remover os assuntos
		String sql = "delete from Assunto where id_disciplina = ?";
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, disciplina.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// remover as questoes
		/*sql = "delete from questao where id_disciplina = ?";
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, disciplina.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*sql = "delete from Prova where id_disciplina = ?";
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, disciplina.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "delete from Questao where id_disciplina = ?";
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, disciplina.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// finalmente remove a disciplina
		sql = "delete from Disciplina where codigo = ?";
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, disciplina.getCodigo());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizar(DisciplinaVO disciplina) throws SQLException {
		String sql = "update Disciplina set nome = ?, codigo = ? where id = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, disciplina.getNome());
			ptst.setNString(2, disciplina.getCodigo());
			ptst.setLong(3, disciplina.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		}
	}

	public ResultSet listarPorId(DisciplinaVO disciplina) {
		String sql = "select * from Disciplina where id = ?";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, disciplina.getId());
			rs = ptst.executeQuery();
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
			rs = ptst.executeQuery();
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
			ptst.setNString(1, disciplina.getCodigo());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet listar() {
		String sql = "select * from Disciplina";
		Statement st;
		ResultSet rs;
		rs = null;

		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
