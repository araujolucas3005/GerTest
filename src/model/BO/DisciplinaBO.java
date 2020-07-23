package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import exception.InsertException;
import exception.NomeMuitoLongException;

import java.util.List;
import model.DAO.DisciplinaDAO;
import model.VO.BiologicaVO;
import model.VO.DisciplinaVO;
import model.VO.ExataVO;
import model.VO.HumanaVO;

public class DisciplinaBO extends BaseBO<DisciplinaVO> {

	private static DisciplinaDAO dDao = new DisciplinaDAO();

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
		ResultSet rs = dDao.listarPorId(disciplina);
		DisciplinaVO disc = null;

		try {
			if (rs.next()) {
				if (rs.getString("Codigo").substring(0, 3).equals("EXA")) {
					disc = new ExataVO();
					disc.setId(rs.getLong("id"));
					disc.setCodigo(rs.getString("codigo"));
					disc.setNome(rs.getString("nome"));
				} else if (rs.getString("Codigo").substring(0, 3).equals("BIO")) {
					disc = new BiologicaVO();
					disc.setId(rs.getLong("id"));
					disc.setCodigo(rs.getString("codigo"));
					disc.setNome(rs.getString("nome"));
				} else {
					disc = new HumanaVO();
					disc.setId(rs.getLong("id"));
					disc.setCodigo(rs.getString("codigo"));
					disc.setNome(rs.getString("nome"));
				}
			} else {
				throw new InsertException("Não existe disciplina com esse ID!");
			}
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disc;
	}

	public List<DisciplinaVO> listar() {
		ResultSet rs = dDao.listar();
		List<DisciplinaVO> disciplinas = new ArrayList<>();
		DisciplinaVO disciplina = null;

		try {
			while (rs.next()) {
				if (rs.getString("Codigo").substring(0, 3).equals("EXA")) {
					disciplina = new ExataVO();
					disciplina.setId(rs.getLong("id"));
					disciplina.setCodigo(rs.getString("codigo"));
					disciplina.setNome(rs.getString("nome"));
				} else if (rs.getString("Codigo").substring(0, 3).equals("BIO")) {
					disciplina = new BiologicaVO();
					disciplina.setId(rs.getLong("id"));
					disciplina.setCodigo(rs.getString("codigo"));
					disciplina.setNome(rs.getString("nome"));
				} else {
					disciplina = new HumanaVO();
					disciplina.setId(rs.getLong("id"));
					disciplina.setCodigo(rs.getString("codigo"));
					disciplina.setNome(rs.getString("nome"));
				}
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