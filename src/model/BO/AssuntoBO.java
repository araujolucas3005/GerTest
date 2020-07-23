package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.AssuntoMuitoLongoException;
import exception.DisciplinaNaoSelecionadaException;
import exception.InsertException;
import model.DAO.AssuntoDAO;
import model.DAO.AssuntoInterDAO;
import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.QuestaoVO;

public class AssuntoBO<VO extends AssuntoVO> extends BaseBO<VO> implements AssuntoInterBO<VO> {

	private static AssuntoInterDAO<AssuntoVO> dao = new AssuntoDAO<>();

	@Override
	public void cadastrar(VO vo) throws AssuntoMuitoLongoException, Exception {
		ResultSet rs = dao.listarPorConteudo(vo);

		if (rs.next()) {
			throw new Exception();
		} else {
			try {
			dao.inserir(vo);
			} catch (AssuntoMuitoLongoException e) {
				throw new AssuntoMuitoLongoException();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<VO> listarPorDisciplina(VO vo) {
		
		ResultSet rs = dao.listarPorDisciplina(vo);
		List<VO> assuntos = new ArrayList<>();

		try {
			while (rs.next()) {
				AssuntoVO newAssunto = new AssuntoVO();
				newAssunto.setId(rs.getLong("id"));
				newAssunto.setConteudo(rs.getString("conteudo"));
				newAssunto.setIdDisciplina(rs.getLong("id_disciplina"));
				assuntos.add((VO)newAssunto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assuntos;
	}

	@Override
	public VO buscarPorId(VO vo) {
		try {
			ResultSet rs = dao.listarPorId(vo);
			if (rs.next()) {
				vo.setConteudo(rs.getString("conteudo"));
				vo.setId(rs.getLong("id"));
				vo.setIdDisciplina(rs.getLong("id_disciplina"));
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
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VO> listar() throws InsertException {
		AssuntoVO assunto = null;
		List<VO> assuntos = new ArrayList<>();

		try {
			ResultSet rs = dao.listar();
			while (rs.next()) {
				assunto = new AssuntoVO();
				assunto.setConteudo(rs.getString("conteudo"));
				assunto.setId(rs.getLong("id"));
				assunto.setIdDisciplina(rs.getLong("id_disciplina"));
				assuntos.add((VO)assunto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assuntos;
	}

	@Override
	public void alterar(VO vo) throws Exception {
		ResultSet rs = dao.listarPorConteudo(vo);

		if (rs.next()) {
			throw new Exception();
		} else {
			dao.atualizar(vo);
		}
	}

	@Override
	public void remover(VO vo) throws InsertException {
		// TODO Auto-generated method stub
		ResultSet rs = dao.listarPorConteudo(vo);

		try {
			if (rs.next()) {
				dao.remover(vo);
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

	@SuppressWarnings("unchecked")
	public List<VO> listarPorDisciplina(DisciplinaVO disciplina) throws SQLException {
		ResultSet rs = dao.listarPorDisciplina(disciplina);
		List<VO> assuntos = new ArrayList<>();

		try {
			while (rs.next()) {
				AssuntoVO newAssunto = new AssuntoVO();
				newAssunto.setId(rs.getLong("id"));
				newAssunto.setConteudo(rs.getString("conteudo"));
				newAssunto.setIdDisciplina(rs.getLong("id_disciplina"));
				assuntos.add((VO)newAssunto);
			}
		} catch (DisciplinaNaoSelecionadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assuntos;		
	}

	@SuppressWarnings("unchecked")
	public List<VO> listarPorDisciplina(QuestaoVO questao) {
		ResultSet rs = dao.listarPorDisciplina(questao);
		List<VO> assuntos = new ArrayList<>();
		try {
			while (rs.next()) {
				AssuntoVO newAssunto = new AssuntoVO();
				newAssunto.setId(rs.getLong("id"));
				newAssunto.setConteudo(rs.getString("conteudo"));
				newAssunto.setIdDisciplina(rs.getLong("id_disciplina"));
				assuntos.add((VO)newAssunto);
			}			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return assuntos;
	}
}
