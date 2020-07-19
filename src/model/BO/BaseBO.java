package model.BO;

import java.util.List;

import exception.InsertException;
import exception.NotFoundException;

public class BaseBO<VO> implements BaseInterBO<VO>{
 
	@Override
	public void cadastrar(VO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VO buscarPorId(VO vo) throws NotFoundException {
		return vo;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VO> listar() throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(VO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(VO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

}