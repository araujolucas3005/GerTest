package model.BO;

import java.util.List;

import exception.InsertException;
import exception.NotFoundException;

public interface BaseInterBO<VO> {
	public void cadastrar(VO vo) throws Exception;
	public VO buscarPorId(VO vo) throws NotFoundException;
	public List<VO> listar() throws InsertException;
	public void alterar(VO vo) throws Exception;
	public void remover(VO vo) throws InsertException;
	public void testar();
}

