package model.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.VO.DiscursivaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.QuestaoVO;
import model.VO.VerdadeiroOuFalsoVO;

public class QuestaoDAO<VO extends QuestaoVO> extends BaseDAO<VO>{
	
	
	public void inserir(QuestaoVO vo) {
		String sql = "insert into questao (codigo,nivel,gabarito,enunciado,tipo, idAssuntos) values (?,?,?,?,?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setString(1, vo.getCodigo());
			ptst.setInt(2, vo.getNivel());
			ptst.setString(3, vo.getGabarito());
			ptst.setString(4,vo.getEnunciado());
			ptst.setString(5,vo.getTipo());
			ptst.setLong(6, vo.getIdAssuntos());
			
			int affectedRows = ptst.executeUpdate();
			
			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada.");
			}
			ResultSet generatedKeys = ptst.getGeneratedKeys();
			if (generatedKeys.next()) {
				vo.setIdQuestao(generatedKeys.getLong(1));
			}
			else {
				throw new SQLException("A insercao falhou. Nenhum id foi retornado.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void atualizar(QuestaoVO vo) {
		String sql = "update questao set nivel = ? where codigo = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, vo.getNivel());
			ptst.setString(2, vo.getCodigo());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remover(QuestaoVO vo) {
		String sql = "delete from questao where codigo = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCodigo());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ResultSet listar() throws SQLException{
		String sql = "select * from questao";
		Statement st;
		ResultSet rs = null;
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
			List<QuestaoVO> questoes = new ArrayList<QuestaoVO>();
			while(rs.next()) {
				if (rs.getString(6).equals("D")) {
					QuestaoVO vo = new DiscursivaVO();
					vo.setCodigo(rs.getString("codigo"));
					vo.setEnunciado(rs.getString("enunciado"));
					vo.setGabarito(rs.getString("gabarito"));
					vo.setNivel(rs.getInt("nivel"));
					questoes.add(vo);
				}
				if (rs.getString(6).equals("ME")) {
					QuestaoVO vo = new MultiplaEscolhaVO();
					vo.setCodigo(rs.getString("codigo"));
					vo.setEnunciado(rs.getString("enunciado"));
					vo.setGabarito(rs.getString("gabarito"));
					vo.setNivel(rs.getInt("nivel"));
					questoes.add(vo);
				}
				if (rs.getString(6).equals("VoF")) {
					QuestaoVO vo = new VerdadeiroOuFalsoVO();
					vo.setCodigo(rs.getString("codigo"));
					vo.setEnunciado(rs.getString("enunciado"));
					vo.setGabarito(rs.getString("gabarito"));
					vo.setNivel(rs.getInt("nivel"));
					questoes.add(vo);
				}
			}
			
			for (QuestaoVO vo2 : questoes) {
				System.out.println(vo2);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet listarPorId(VO vo) throws SQLException{
		ResultSet rs = null;
		return rs;
	}
	
}