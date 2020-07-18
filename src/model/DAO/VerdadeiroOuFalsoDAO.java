package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.MultiplaEscolhaVO;
import model.VO.VerdadeiroOuFalsoVO;

public class VerdadeiroOuFalsoDAO extends ObjetivaDAO<VerdadeiroOuFalsoVO>{
	
	public void inserir(VerdadeiroOuFalsoVO vo) throws SQLException {
		super.inserir(vo);
		String sql = "insert into VeradeiroOuFalso (id_questao, id_objetiva) values (?)";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1,  vo.getIdQuestao());
			ptst.setLong(2, vo.getIdObjetiva());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remover(VerdadeiroOuFalsoVO vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from VeradeiroOuFalso where id_questao = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getIdQuestao());
			ptst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.remover(vo);
	}

	public ResultSet listar() {
		String sql = "select * from Questao inner join VerdadeiroOuFalso on Questao.id = VerdadeiroOuFalso.id_questao";
		
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
	
	public ResultSet buscarPorId(MultiplaEscolhaVO vo) {
		String sql = "select * from VerdadeiroOuFalso where id_questao = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
		ptst = getConnection().prepareStatement(sql);
		ptst.setLong(1, vo.getIdQuestao());
		rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
