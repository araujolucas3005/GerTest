 
package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.OpcaoVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;
import model.VO.UsuarioVO;

public abstract class BaseInterDAO<VO> extends BaseDAO<VO> {
	public void inserir(VO vo) throws Exception {
		
	}
	public void remover(VO vo)throws SQLException {
		
	}
	public void atualizar(VO vo) throws SQLException{
		
	}
	public ResultSet listar() throws SQLException{
		return null;
	}
	public ResultSet listarPorId(VO vo) throws SQLException{
		return null;
	}
	public ResultSet listarPorConteudo(VO vo) {
		return null;
	}
	public ResultSet listarPorDisciplina(AssuntoVO assunto) {
		return null;
	}
	public ResultSet listarPorDisciplina(DisciplinaVO disciplina) throws SQLException{
		return null;
	}
	public ResultSet listarPorDisciplina(QuestaoVO questao) throws SQLException{
		return null;
	}
	public ResultSet listarPorCodigo(DisciplinaVO disciplina) {
		return null;
	}
	public ResultSet listarPorNome(DisciplinaVO disciplina) {
		return null;
	}
	public ResultSet listarPorCodigo(QuestaoVO vo) throws SQLException {
		return null;
	}
	public void removerDaProva(VO questao) {	
	}
	public ResultSet buscarPorLogin(VO vo) {
		return null;
	}
	public ResultSet listarPorQuestao(OpcaoVO opcao) {
		return null;
	}
	public void inserirQuestaoAvulsa(ProvaVO prova, QuestaoVO questao) {		
	}
	public ResultSet listarPorDisciplina(ProvaVO prova) {
		return null;
	}
	public ResultSet listarPorLogin(UsuarioVO vo) {
		return null;
	}
	
	//Metodo para conecção;
	public static Connection getConnection(){
		if (getConn() == null) {
			try {
				setConn(DriverManager.getConnection(getUrl(),getUser(),getSenha()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getConn();			
		}
		else return getConn();
	}
	
	public static void closeConnection() {
		if (getConn() != null) {
			try {
				getConn().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}