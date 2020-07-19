package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InsertException;
import model.DAO.AssuntoDAO;
import model.VO.AssuntoVO;

public class AssuntoBO extends BaseBO<AssuntoVO> {

	private static AssuntoDAO dao = new AssuntoDAO();

	@Override
	public void cadastrar(AssuntoVO assunto) throws InsertException {
		ResultSet rs = dao.listarPorConteudo(assunto);

		try {
			if (rs.next()) {
				throw new InsertException("Assunto ja cadastrado!");
			} else {
				dao.inserir(assunto);
			}
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}
	
	public List<AssuntoVO> listarPorDisciplina(AssuntoVO assunto) {
		ResultSet rs = dao.listarPorDisciplina(assunto);
		List<AssuntoVO> assuntos = new ArrayList<>();
		
			try {
				while (rs.next()) {
					AssuntoVO newAssunto = new AssuntoVO();
					newAssunto.setId(rs.getLong("id"));
					newAssunto.setConteudo(rs.getString("conteudo"));
					newAssunto.setIdDisciplina(rs.getLong("id_disciplina"));
					assuntos.add(newAssunto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return assuntos;
	}

	@Override
	public AssuntoVO buscarPorId(AssuntoVO assunto) {
		ResultSet rs = dao.listarPorId(assunto);
		AssuntoVO assunt = null;
		
			try {
				if (rs.next()) {
					assunt = new AssuntoVO();
					assunt.setConteudo(rs.getString("conteudo"));
					assunt.setId(rs.getLong("id"));
					assunto.setIdDisciplina(rs.getLong("id_disciplina"));
				} else {
					throw new InsertException("Não existe esse assunto!");
				}
			} catch (InsertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return assunt;
	}

	@Override
	public List<AssuntoVO> listar() throws InsertException {
		ResultSet rs = dao.listar();
		AssuntoVO assunto = null;
		List<AssuntoVO> assuntos = new ArrayList<>();
		
			try {
				while (rs.next()) {
					assunto = new AssuntoVO();
					assunto.setConteudo(rs.getString("conteudo"));
					assunto.setId(rs.getLong("id"));
					assunto.setIdDisciplina(rs.getLong("id_disciplina"));
					assuntos.add(assunto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return assuntos;
	}

	@Override
	public void alterar(AssuntoVO assunto) throws InsertException {
		ResultSet rs = dao.listarPorConteudo(assunto);
		
			try {
				if (rs.next()) {
					throw new InsertException("Ja existe um assunto com esse conteudo!");
				} else {
					dao.atualizar(assunto);
				}
			} catch (InsertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	@Override
	public void remover(AssuntoVO assunto) throws InsertException {
		// TODO Auto-generated method stub
		ResultSet rs = dao.listarPorId(assunto);
		
			try {
				if (rs.next()) {
					dao.remover(assunto);
				} else {
					throw new InsertException("Esse assunto nao existe!");
				}
			} catch (InsertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
