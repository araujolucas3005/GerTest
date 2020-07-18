package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.QuestaoVO;

public class QuestaoDAO<VO extends QuestaoVO> extends BaseDAO<VO> {

	@Override
	public void inserir(VO vo) throws SQLException {
		String sql = "insert into Questao (codigo,nivel,enunciado,id_disciplina,gabarito,id_assunto) values (?,?,?,?,?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setString(1, vo.getCodigo());
			ptst.setInt(2, vo.getNivel());
			ptst.setString(3, vo.getEnunciado());
			ptst.setLong(4, vo.getIdDisciplina());
			ptst.setString(5, vo.getGabarito());
			ptst.setLong(6, vo.getIdAssunto());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
			ResultSet generatedKeys = ptst.getGeneratedKeys();
			if (generatedKeys.next()) {
				vo.setIdQuestao(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A inserção falhou. Nenhum id foi retornado.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void remover(VO vo) throws SQLException {
		String sql = "delete from Questao where id = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getIdQuestao());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(VO vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update Questao set codigo = ? set nivel = ? set id_assunto = ? set enunciado = ? set gabarito = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCodigo());
			ptst.setInt(2, vo.getNivel());
			ptst.setLong(3, vo.getIdAssunto());
			ptst.setString(4, vo.getEnunciado());
			ptst.setString(5, vo.getGabarito());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "select * from Questao";
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

	@Override
	public ResultSet listarPorId(VO vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from Questao where id = ?";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getIdQuestao());
			rs = ptst.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet listarPorCodigo(VO vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from Questao where codigo = ?";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCodigo());
			rs = ptst.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
