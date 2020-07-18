package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import model.DAO.DisciplinaDAO;
import model.VO.BiologicaVO;
import model.VO.DisciplinaVO;
import model.VO.ExataVO;
import model.VO.HumanaVO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public class DisciplinaBO extends BaseBO<DisciplinaVO> {

	private static DisciplinaDAO dDao = new DisciplinaDAO();

	public void cadastrar(DisciplinaVO disciplina) {
		ResultSet rs = dDao.listarPorCodigo(disciplina);
		ResultSet rs2 = dDao.listarPorNome(disciplina);
		try {
			if (rs.next()) {
				throw new InsertException("Já existe disciplina com esse código!");
			} else if (rs2.next()) {
				throw new InsertException("Já existe disciplina com esse nome!");
			} else {
				dDao.inserir(disciplina);
			}
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());

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
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());
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
			throw new InsertException(e.getMessage());
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
		} catch (SQLException e) {
			throw new InsertException(e.getMessage());

		}
	}

	public void remover(DisciplinaVO disciplina) {
		ResultSet rs = dDao.listarPorCodigo(disciplina);
		try {
			if (!rs.next()) {
				throw new InsertException("Essa disciplina não existe!");
			} else {
				dDao.remover(disciplina);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());

		}
	}
}