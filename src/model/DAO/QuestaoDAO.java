package model.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.VO.DiscursivaVO;
import model.VO.QuestaoVO;

public class QuestaoDAO extends BaseDAO implements QuestaoInterDAO{
	
	
	public void inserir(QuestaoVO vo, String optativa) {
		conn = getConnection();
		String sql = "insert into questao (codigo,nivel,gabarito,enunciado) values (?,?,?,?)";
		PreparedStatement ptst;
		try {
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, vo.getCodigo());
			ptst.setInt(2, vo.getNivel());
			ptst.setString(3, vo.getGabarito());
			ptst.setString(4,vo.getEnunciado());
			ptst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<QuestaoVO> listar() {
		conn = getConnection();
		String sql = "select * from questao";
		Statement st;
		ResultSet rs;
		List<QuestaoVO> questoes = new ArrayList<QuestaoVO>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				QuestaoVO vo = new DiscursivaVO();
				vo.setCodigo(rs.getString("codigo"));
				vo.setEnunciado(rs.getString("enunciado"));
				vo.setGabarito(rs.getString("gabarito"));
				vo.setNivel(rs.getInt("nivel"));
				questoes.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questoes;
		
	}
	
	public void editar(QuestaoVO vo) {
		conn = getConnection();
		String sql = "update questao set nivel = ? where codigo = ?";
		PreparedStatement ptst;
		try {
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, vo.getNivel());
			ptst.setString(2, vo.getCodigo());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public QuestaoVO buscarByCodigo(String codigo) {
		conn = getConnection();
		String sql = "select * from questao where codigo = " + "'" + codigo + "'";
		Statement st;
		ResultSet rs;
		QuestaoVO vo1 = new DiscursivaVO();
		try {		
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				vo1.setCodigo(rs.getString("codigo"));
				vo1.setEnunciado(rs.getString("enunciado"));
				vo1.setGabarito(rs.getString("gabarito"));
				vo1.setNivel(rs.getInt("nivel"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo1;
	}
	
	public QuestaoVO buscarByNivel(int nivel) {
		conn = getConnection();
		String sql = "select * from questao where nivel = " + nivel;
		Statement st;
		ResultSet rs;
		QuestaoVO vo1 = new DiscursivaVO();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				vo1.setCodigo(rs.getString("codigo"));
				vo1.setEnunciado(rs.getString("enunciado"));
				vo1.setGabarito(rs.getString("gabarito"));
				vo1.setNivel(rs.getInt("nivel"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo1;
	
	}
	
	public void remover(QuestaoVO vo) {
		conn = getConnection();
		String sql = "delete from questao where codigo = ?";
		PreparedStatement ptst;
		try {
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, vo.getCodigo());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}