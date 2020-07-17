package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.UsuarioVO;

public class UsuarioDAO <VO extends UsuarioVO> extends BaseDAO<VO> {
	
	public void inserir(VO usuario) {
		
		String sql = "insert into Usuario (login,senha,nome,email) values (?,?,?,?)";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, usuario.getLogin());
			ptst.setNString(2, usuario.getSenha());
			ptst.setNString(3, usuario.getNome());
			ptst.setNString(4, usuario.getEmail());
			
			int affectedRows = ptst.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remover(VO usuario) {

		String sql = "delete from Usuario where login = ?";
		PreparedStatement ptst = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, usuario.getLogin());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizar(VO usuario) {
		String sql = "update Usuario where set senha = ?, email = ?, nome = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, usuario.getSenha());
			ptst.setNString(2, usuario.getEmail());
			ptst.setNString(3, usuario.getNome());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet listar() {
		String sql = "select * from Usuario";
		ResultSet rs = null;
		Statement st;
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet listarPorLogin(VO usuario) {
		String sql = "select * from Usuario where login = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setNString(1, usuario.getLogin());
			rs = ptst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet listarPorId(VO usuario) {
		String sql = "select * from Usuario where id = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, usuario.getId());
			rs = ptst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
