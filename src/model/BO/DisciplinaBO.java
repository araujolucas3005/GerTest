package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import exception.InsertException;
import exception.NomeMuitoLongException;

import java.util.List;
import model.DAO.DisciplinaDAO;
import model.DAO.DisciplinaInterDAO;
import model.VO.DisciplinaVO;

public class DisciplinaBO extends BaseBO<DisciplinaVO> implements BaseInterBO<DisciplinaVO> {

	private static DisciplinaInterDAO<DisciplinaVO> dDao = new DisciplinaDAO<>();

	public void cadastrar(DisciplinaVO disciplina) throws Exception {
		ResultSet rs = dDao.listarPorCodigo(disciplina);
		ResultSet rs2 = dDao.listarPorNome(disciplina);
		if (rs.next()) {
			throw new Exception();
		}
		if (rs2.next()) {
			throw new Exception();
		}
		try {
			dDao.inserir(disciplina);
		} catch (NomeMuitoLongException e) {
			throw new NomeMuitoLongException();
		}
	}

	public DisciplinaVO buscarPorId(DisciplinaVO disciplina) {

		try {
			ResultSet rs = dDao.listarPorId(disciplina);
			if (rs.next()) {
				if (rs.getString("Codigo").substring(0, 3).equals("EXA")) {
					disciplina.setId(rs.getLong("id"));
					disciplina.setCodigo(rs.getString("codigo"));
					disciplina.setNome(rs.getString("nome"));
				}
				throw new InsertException("Não existe disciplina com esse ID!");
			}
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disciplina;
	}

	public List<DisciplinaVO> listar() {
		List<DisciplinaVO> disciplinas = new ArrayList<>();

		try {
			ResultSet rs = dDao.listar();
			while (rs.next()) {
				DisciplinaVO disciplina = new DisciplinaVO();
				disciplina.setNome(rs.getString("nome"));
				disciplina.setCodigo(rs.getString("codigo"));
				disciplina.setId(rs.getLong("id"));
				disciplinas.add(disciplina);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return disciplinas;
	}

	public void alterar(DisciplinaVO disciplina) {
		ResultSet rs = dDao.listarPorCodigo(disciplina);
		try {
			if (rs.next()) {
				throw new InsertException("Ja existe disciplina com esse codigo!");
			} else {
				dDao.atualizar(disciplina);
			}
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover(DisciplinaVO disciplina) {
		ResultSet rs = dDao.listarPorCodigo(disciplina);
		try {
			if (rs.next()) {
				dDao.remover(disciplina);
			} else {
				throw new InsertException("essa disciplina nao existe!");
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