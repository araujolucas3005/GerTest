package model.BO;

import java.sql.SQLException;
import java.util.List;

import exception.AutenticationException;
import exception.InsertException;
import exception.NotFoundException;
import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;
import model.VO.UsuarioVO;

public abstract class BaseInterBO<VO> {
	public void cadastrar(VO vo) throws Exception{		
	}
	public VO buscarPorId(VO vo) throws NotFoundException, SQLException{
		return null;
	}
	public List<VO> listar() throws InsertException, SQLException{
		return null;
	}	
	public void alterar(VO vo) throws Exception {	
	}
	public void remover(VO vo) throws InsertException, Exception {		
	}	
	public UsuarioVO autenticar(VO vo) throws AutenticationException {
		return null;		
	}
	public List<QuestaoVO> listarQuestoes(ProvaVO lastSelectedProva) {
		return null;
	}
	public void cadastrarQuestaoAvulsa(ProvaVO lastSelectedProva, QuestaoVO selectedItem) {		
	}
	public List<AssuntoVO> listarPorDisciplina(AssuntoVO assunto) {
		return null;
	}
	public List<AssuntoVO> listarPorDisciplina(DisciplinaVO selectedItem) throws SQLException {
		return null;
	}
	public List<ProvaVO> listarPorDisciplina(ProvaVO prova) {
		return null;
	}
	public List<QuestaoVO> listarTodos() throws SQLException {
		return null;
	}
	public void removerDaProva(QuestaoVO selectedItem) {		
	}

}

