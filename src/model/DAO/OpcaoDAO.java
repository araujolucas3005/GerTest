package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.OpcaoVO;

public class OpcaoDAO extends BaseDAO<OpcaoVO>{
		
	public void inserir(OpcaoVO opcao) {
		String sql = "insert into Opcao (conteudo, id_questao) values (?,?)";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, opcao.getConteudo());
			ptst.setLong(2, opcao.getIdQuestao());
			
			int affectedRows = ptst.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remover(OpcaoVO opcao) {
		String sql = "delete from Opcao where id = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, opcao.getId());
			ptst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizar(OpcaoVO opcao) {
		String sql = "update Opcao set conteudo = ? where id = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, opcao.getConteudo());
			ptst.setLong(2, opcao.getId());
			ptst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet listar() {
		String sql = "select * from Opcao";
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

	public ResultSet listarPorId(OpcaoVO opcao) {
		String sql = "select * from Opcao where id = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, opcao.getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet listarPorQuestao(OpcaoVO opcao) {
		String sql = "select * from Opcao where id_objetiva = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, opcao.getIdQuestao());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet listarPorConteudo(OpcaoVO opcao) {
		String sql = "select * from Opcao where conteudo = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, opcao.getConteudo());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
