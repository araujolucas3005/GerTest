package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VO.DiscursivaVO;
import model.VO.QuestaoVO;

public class DiscursivaDAO extends QuestaoDAO<DiscursivaVO>{ 

	public QuestaoVO listarDiscursiva(List<QuestaoVO> quest, ResultSet rs) throws SQLException{
		QuestaoVO vo = new DiscursivaVO();
		vo.setCodigo(rs.getString("codigo"));
		vo.setEnunciado(rs.getString("enunciado"));
		vo.setGabarito(rs.getString("gabarito"));
		vo.setNivel(rs.getInt("nivel"));
		return vo;
	}
}