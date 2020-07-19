 
package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseInterDAO<VO> {
	public abstract void inserir(VO vo) throws SQLException;
	public abstract void remover(VO vo)throws SQLException;
	public abstract void atualizar(VO vo) throws SQLException;
	public abstract ResultSet listar() throws SQLException;
	public abstract ResultSet listarPorId(VO vo) throws SQLException;
}