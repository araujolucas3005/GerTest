package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.DiscursivaVO;

public class DiscursivaDAO extends QuestaoDAO<DiscursivaVO> {
	@Override
	public void inserir(DiscursivaVO vo) {
		try {
			super.inserir(vo);
			String sql = "insert into Discursiva (id_questao) values (?)";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getIdQuestao());
			int affectedRows = ptst.executeUpdate();
	        if (affectedRows == 0) {
	        	throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
		    }
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void remover(DiscursivaVO vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from Discursiva where id_questao = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getIdQuestao());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.remover(vo);
	}
	
	public ResultSet listar() {
		String sql = "select * from Questao inner join Discursiva on Questao.id = Discursiva.id_questao"; 
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
	
	public ResultSet buscarPorIdQuestao(DiscursivaVO vo) {
		String sql = "select * from Discursiva where id_questao = ?";
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
	
	public ResultSet listarQuestaoProva() {
		String sql = "select * from Discursiva inner join Prova_Questao on Discursiva.id = Prova_Questao.id_questao";
		ResultSet rs = null;
		Statement st;
		
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
