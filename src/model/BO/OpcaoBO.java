package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.OpcaoDAO;
import model.VO.AssuntoVO;
import model.VO.OpcaoVO;

public class OpcaoBO extends BaseBO<OpcaoVO>{
	
	OpcaoDAO dao = new OpcaoDAO();
	
	@Override
	public void cadastrar(OpcaoVO opcao) throws InsertException {
		ResultSet rs = dao.listarPorConteudo(opcao);

		try {
			if (rs.next()) {
				throw new Exception("Assunto ja cadastrado!");
			} else {
				dao.inserir(opcao);
			}
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}
	
	public List<OpcaoVO> listarPorQuestao(OpcaoVO opcao) throws InsertException {
		ResultSet rs = dao.listarPorQuestao(opcao);
		List<OpcaoVO> opcoes = new ArrayList<>();
		
		try {
			while (rs.next()) {
				OpcaoVO newOpcao = new OpcaoVO();
				newOpcao.setId(rs.getLong("id"));
				newOpcao.setConteudo(rs.getString("conteudo"));
				newOpcao.setIdQuestao(rs.getLong("id_questao"));
				opcoes.add(newOpcao);
			}
		} catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return opcoes;
	}

	@Override
	public OpcaoVO buscarPorId(OpcaoVO opcao) throws InsertException {
		ResultSet rs = dao.listarPorId(opcao);
		OpcaoVO op = null;
		
		try {
			if (rs.next()) {
				op = new OpcaoVO();
				op.setConteudo(rs.getString("conteudo"));
				op.setId(rs.getLong("id"));
				op.setIdQuestao(rs.getLong("id_disciplina"));
			} else {
				throw new Exception("Não existe esse assunto!");
			}
		} catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return op;
	}

	@Override
	public List<OpcaoVO> listar() throws InsertException {
		ResultSet rs = dao.listar();
		OpcaoVO opcao = null;
		List<OpcaoVO> opcoes = new ArrayList<>();
		
		try {
			while (rs.next()) {
				opcao = new OpcaoVO();
				opcao.setConteudo(rs.getString("conteudo"));
				opcao.setId(rs.getLong("id"));
				opcao.setIdQuestao(rs.getLong("id_disciplina"));
				opcoes.add(opcao);
			}
		} catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return opcoes;
	}

	@Override
	public void alterar(OpcaoVO opcao) {
		ResultSet rs = dao.listarPorConteudo(opcao);
		
		try {
			if (rs.next()) {
				throw new InsertException("Ja existe uma opcao com esse conteudo!");
			} else {
				dao.atualizar(opcao);
			}
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}

	@Override
	public void remover(OpcaoVO opcao) throws InsertException {
		// TODO Auto-generated method stub
		ResultSet rs = dao.listarPorConteudo(opcao);
		
		try {
			if (rs.next()) {
				dao.remover(opcao);
			} else {
				throw new InsertException("Esse assunto nao existe!");
			}
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}
}
